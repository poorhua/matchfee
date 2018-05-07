CREATE or REPLACE VIEW v_pay_ticket
AS
SELECT a.charge_id, max(a.pay_date) pay_date, sum(a.money) money
FROM tpay_ticket a
GROUP BY a.charge_id;

CREATE or REPLACE VIEW v_project_license
AS
SELECT a.charge_id, 
sum(a.up_area+a.down_area) area, 
GROUP_CONCAT(a.document_no) documentlist 
FROM tproject_license a
GROUP BY a.charge_id;

CREATE or REPLACE VIEW v_deduction_doc
AS
SELECT b.charge_id, sum(a.area) area
FROM tdeduction_doc_item a
LEFT JOIN tdeduction_doc b on b.id=a.doc_id
GROUP BY b.charge_id;

CREATE or REPLACE VIEW v_project_deduction
AS
SELECT a.charge_id, sum(a.money) money
FROM tproject_deduction a
GROUP BY a.charge_id;