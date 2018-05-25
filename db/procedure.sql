-- ----------------------------
-- Procedure structure for `proc_refresh_land_pay_money`
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_refresh_land_pay_money`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_refresh_land_pay_money`(IN chargeId CHAR(20), 
IN prjNum CHAR(20), IN matchfeeBasis INT, OUT deductMoney decimal(15,2))
BEGIN
	
	Declare landPayTicketTotal decimal(15,2) default 0;
	Declare licenseUpAreaTotal decimal(15,2) default 0;
	Declare previousDeductTotal decimal(15,2) default 0;
    Declare sumMoneyExcludeLandPay decimal(15,2) default 0;
	Declare deductRemain decimal(15,2) default 0;
	
	#国土总的缴费A
	
	select SUM(a.money) into landPayTicketTotal
	from tland_pay_ticket a
	where a.prj_num=prjNum
	AND a.del_flag = '0';
	
	IF landPayTicketTotal is NULL THEN set landPayTicketTotal=0; 
	END IF;
	
	#规划许可证合计B
	
	select SUM(b.up_area)*matchfeeBasis into licenseUpAreaTotal
	from tproject_license b
	where b.charge_id=chargeId
	AND b.del_flag = '0';
	
	IF licenseUpAreaTotal is NULL THEN set licenseUpAreaTotal=0;
	END IF;
	
	#之前抵扣合计C
	
	select sum(c.land_pay_money) into previousDeductTotal
	from tcharge c
	where c.prj_num=prjNum
	AND c.id<chargeId
	AND c.del_flag = '0';
	
	IF previousDeductTotal is NULL THEN set previousDeductTotal=0;
	END IF;

  #本期其他抵扣项合计D

	select sum(u.money) into sumMoneyExcludeLandPay from
	(
    select CONCAT('pre',p.id), (p.cal_money - p.pay_money) money, '0' del_flag
	from tcharge p
    where p.prj_num=prjNum
    and p.id = (select max(id) from tcharge q where q.prj_num=prjNum and q.id<chargeId)
	union	
	select CONCAT('pl',a.id), (a.up_area+a.down_area)*matchfeeBasis money, a.del_flag
	from tproject_license a
	where a.charge_id=chargeId
	union
	select CONCAT('dd',b.id), (0-b.money) money, b.del_flag
	from tdeduction_doc_item b
	left join tdeduction_doc doc on doc.id=b.doc_id
	where doc.charge_id=chargeId
	union
	select CONCAT('pd',c.id), (0-c.money) money, c.del_flag
	from tproject_deduction c
	where c.charge_id=chargeId	
	) u
	where u.del_flag = '0';

    set deductRemain = landPayTicketTotal - previousDeductTotal;
    
    set deductMoney = 0;
	IF deductRemain>0 THEN
	   IF deductRemain <= licenseUpAreaTotal THEN
	      set deductMoney = deductRemain;
	   ELSE
	      set deductMoney = licenseUpAreaTotal;
	   END IF;
	END IF;

  #deductMoney can not exceed sumMoneyExcludeLandPay
  IF deductMoney>sumMoneyExcludeLandPay THEN
     IF sumMoneyExcludeLandPay>0 THEN
        set deductMoney = sumMoneyExcludeLandPay;
     END IF;
  END IF;
	
	UPDATE tcharge 
	set land_pay_money = deductMoney
	WHERE id=chargeId;
	
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `proc_refresh_cal_money`
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_refresh_cal_money`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_refresh_cal_money`(IN chargeId CHAR(20), 
IN prjNum CHAR(20), IN matchfeeBasis INT, OUT calMoney decimal(15,2))
BEGIN
	
	select sum(u.money) into calMoney from
	(
    select CONCAT('pre',p.id), (p.cal_money - p.pay_money) money, '0' del_flag
	from tcharge p
    where p.prj_num=prjNum
    and p.id = (select max(id) from tcharge q where q.prj_num=prjNum and q.id<chargeId)
	union	
	select CONCAT('pl',a.id), (a.up_area+a.down_area)*matchfeeBasis money, a.del_flag
	from tproject_license a
	where a.charge_id=chargeId
	union
	select CONCAT('dd',b.id), (0-b.money) money, b.del_flag
	from tdeduction_doc_item b
	left join tdeduction_doc doc on doc.id=b.doc_id
	where doc.charge_id=chargeId
	union
	select CONCAT('pd',c.id), (0-c.money) money, c.del_flag
	from tproject_deduction c
	where c.charge_id=chargeId
	union
	select CONCAT('lpm',d.id), (0-d.land_pay_money) money, '0' del_flag
	from tcharge d
	where d.id=chargeId		
	) u
	where u.del_flag = '0';
	
	UPDATE tcharge 
	set cal_money = calMoney
	WHERE id=chargeId;
	
END
;;
DELIMITER ;