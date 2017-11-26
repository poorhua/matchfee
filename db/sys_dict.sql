delete from sys_dict where id like 'approves_%';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('approves_1', '0', '驳回', 'approves', 'approves', 10, '0','1',now(),'1',now()),
('approves_2', '1', '批准', 'approves', 'approves', 20, '0','1',now(),'1',now());


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



