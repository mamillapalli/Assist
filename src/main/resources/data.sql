select * from public.resource_table;

delete from admin.resource_table;
INSERT INTO admin.resource_table (uuid, birth_date, email_address, first_name, joining_date, last_name, active_status) VALUES ('d1a3504c-6b90-48ec-b4ab-a6cce972d01c', '1980-09-23 10:41:14.000000', 'ravi@chinasystems-me.com', 'ravikanth', '2005-09-23 10:41:31.000000', 'mamillapalli', true);

INSERT INTO admin.role_table (role_id, role_name) VALUES ('ce935232-4f78-4581-ab6b-80405e87c6d6', 'LEAVE_ADMIN');
INSERT INTO admin.role_table (role_id, role_name) VALUES ('16186d8b-e7d3-475e-9533-bd317a8144dc', 'LEAVE_APPROVER');
