delete from sys_dict where id like 'approves_%';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('approves_1', '0', '驳回', 'approves', 'approves', 10, '0','1',now(),'1',now()),
('approves_2', '1', '批准', 'approves', 'approves', 20, '0','1',now(),'1',now());

delete from sys_dict where id like 'deduction_type_%';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('deduction_type_1', '1', '不限', 'deduction_type', 'deduction_type', 10, '0','1',now(),'1',now()),
('deduction_type_2', '2', '只限地面面积', 'deduction_type', 'deduction_type', 20, '0','1',now(),'1',now());


delete from sys_dict where id like 'attachment_type_%';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('attachment_type_1', '1', '项目建设条件意见书', 'attachment_type', 'attachment_type', 10, '0','1',now(),'1',now()),
('attachment_type_2', '2', '建设工程规划许可证', 'attachment_type', 'attachment_type', 20, '0','1',now(),'1',now()),
('attachment_type_3', '3', '防空地下室批文', 'attachment_type', 'attachment_type', 30, '0','1',now(),'1',now()),
('attachment_type_4', '4', '设计院出具证明', 'attachment_type', 'attachment_type', 40, '0','1',now(),'1',now()),
('attachment_type_5', '5', '国土已缴费票据', 'attachment_type', 'attachment_type', 50, '0','1',now(),'1',now()),
('attachment_type_6', '6', '滨湖区已缴费票据', 'attachment_type', 'attachment_type', 60, '0','1',now(),'1',now()),
('attachment_type_7', '7', '减免证明', 'attachment_type', 'attachment_type', 70, '0','1',now(),'1',now()),
('attachment_type_8', '8', '其它抵扣项证明', 'attachment_type', 'attachment_type', 80, '0','1',now(),'1',now());


delete from sys_dict where id like 'charge_status_%';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('charge_status_00', '00', '编辑', 'charge_status', 'charge_status', 0, '0','1',now(),'1',now()),
('charge_status_05', '05', '退回', 'charge_status', 'charge_status', 5, '0','1',now(),'1',now()),
('charge_status_10', '10', '已申报待测算', 'charge_status', 'charge_status', 10, '0','1',now(),'1',now()),
('charge_status_20', '20', '已测算待审核', 'charge_status', 'charge_status', 20, '0','1',now(),'1',now()),
('charge_status_30', '30', '已审核待缴费', 'charge_status', 'charge_status', 30, '0','1',now(),'1',now()),
('charge_status_40', '40', '已缴费', 'charge_status', 'charge_status', 40, '0','1',now(),'1',now()),
('charge_status_90', '90', '关闭', 'charge_status', 'charge_status', 90, '0','1',now(),'1',now());



