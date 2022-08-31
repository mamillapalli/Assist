select * from public.resource_table;

create schema admin;

delete from admin.resource_table;
INSERT INTO admin.resource_table (uuid, birth_date, email_address, first_name, joining_date, last_name, active_status) VALUES ('d1a3504c-6b90-48ec-b4ab-a6cce972d01c', '1980-09-23 10:41:14.000000', 'ravikant@chinasystems-me.com', 'ravikanth', '2005-09-23 10:41:31.000000', 'mamillapalli', true);
INSERT INTO admin.resource_table (uuid, birth_date, email_address, first_name, joining_date, last_name, active_status) VALUES ('52f250d8-e6a5-4442-935c-7ee512ac17da', '1980-09-23 10:41:14.000000', 'Sreenivas Reddy', 'Sreenivas', '2005-09-23 10:41:31.000000', 'Reddy', true);
INSERT INTO admin.resource_table (uuid, birth_date, email_address, first_name, joining_date, last_name, active_status) VALUES ('211d1c96-17e2-431d-8219-8715dd11b9d2', '1980-09-23 10:41:14.000000', 'ilyas@chinasystems-me.com', 'Ilyas', '2005-09-23 10:41:31.000000', 'Hussain', true);

INSERT INTO admin.resource_table (uuid, birth_date, email_address, first_name, joining_date, last_name, active_status) VALUES ('5c0c4396-cd95-4b64-adc2-4048f9e4c668', '1980-09-23 10:41:14.000000', 'sreenivas@chinasystems-me.com', 'Sreenivas', '2005-09-23 10:41:31.000000', 'Reddy', true);


INSERT INTO admin.role_table (role_id, role_name,role_desc) VALUES ('ce935232-4f78-4581-ab6b-80405e87c6d6', 'LEAVE_ADMIN','Leave Administrator');
INSERT INTO admin.role_table (role_id, role_name,role_desc) VALUES ('16186d8b-e7d3-475e-9533-bd317a8144dc', 'ADMIN','Organization Administrator');