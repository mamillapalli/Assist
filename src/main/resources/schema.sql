create schema admin;

create table if not exists admin.resource_table
(
    uuid uuid not null
        constraint resource_table_pkey
            primary key,
    authorisation_date timestamp,
    authorised_user varchar(255),
    created_date timestamp,
    created_user varchar(255),
    modified_date timestamp,
    modified_user varchar(255),
    transaction_status varchar(255),
    birth_date timestamp,
    email_address varchar(255)
        constraint uk_h1us76kpm7srsu0nm6ov39xji
            unique,
    first_name varchar(255),
    joining_date timestamp,
    last_name varchar(255),
    reporting_to varchar(255),
    active_status boolean
);

alter table admin.resource_table owner to postgres;

create table if not exists admin.role_table
(
    role_id uuid not null
        constraint role_table_pkey
            primary key,
    authorisation_date timestamp,
    authorised_user varchar(255),
    created_date timestamp,
    created_user varchar(255),
    modified_date timestamp,
    modified_user varchar(255),
    transaction_status varchar(255),
    role_name varchar(255),
    role_desc varchar(255)
);

alter table admin.role_table owner to postgres;

create table if not exists admin.resource_roles_table
(
    resource_id uuid not null
        constraint fkqusel7v3m7ichdl59vdwnrukl
            references admin.resource_table,
    role_id uuid not null
        constraint fk7jt1u9532cdvha9gh0tcnnruf
            references admin.role_table
);

alter table admin.resource_roles_table owner to postgres;

create table if not exists admin.leave_table
(
    leave_id integer not null
        constraint leave_table_pkey
            primary key,
    authorisation_date timestamp,
    authorised_user varchar(255),
    created_date timestamp,
    created_user varchar(255),
    modified_date timestamp,
    modified_user varchar(255),
    transaction_status varchar(255),
    approver_comments varchar(255),
    approver_id integer,
    contact_address varchar(255),
    contact_phone varchar(255),
    delete_flag boolean,
    description varchar(255),
    end_date timestamp,
    name varchar(255),
    number_of_days integer,
    pay_percentage integer,
    resource_id integer,
    start_date timestamp,
    status varchar(255),
    tickets_paid boolean,
    tickets_to varchar(255)
);

alter table admin.leave_table owner to postgres;

create table if not exists admin.audit
(
    uuid uuid not null
        constraint audit_pkey
            primary key,
    accessed_by varchar(255),
    accessed_resource varchar(255),
    event_action varchar(255),
    event_at timestamp,
    exception varchar(255),
    input_parameters varchar(255),
    jwt_token varchar(255),
    remote_address varchar(255),
    remote_host varchar(255),
    remote_user varchar(255),
    returned_result varchar(255)
);

alter table admin.audit owner to postgres;

