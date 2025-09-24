CREATE TABLE users(
  id serial NOT NULL,
  username varchar(50) NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(255) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  phone varchar(20),
  profile_image_url varchar(255),
  is_active boolean NOT NULL,
  is_verified boolean NOT NULL DEFAULT false,
  created_at timestamp DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp,
  CONSTRAINT users_pkey PRIMARY KEY(id)
);


COMMENT ON TABLE users IS 'Tabla para manejo de usuarios';


CREATE TABLE roles(
  id serial NOT NULL,
  name varchar(255) NOT NULL,
  description varchar(255),
  created_at timestamp DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp,
  CONSTRAINT roles_pkey PRIMARY KEY(id)
);


CREATE TABLE users_roles(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY(user_id, role_id)
);


CREATE TABLE email_verification_tokens(
  id serial NOT NULL,
  user_id integer NOT NULL,
  token varchar(255) NOT NULL,
  expire_date timestamp NOT NULL,
  is_revoked boolean DEFAULT false,
  CONSTRAINT email_verification_tokens_pkey PRIMARY KEY(id)
);


CREATE TABLE password_reset_tokens(
  id serial NOT NULL,
  user_id integer NOT NULL,
  token varchar(255) NOT NULL,
  expire_date timestamp NOT NULL,
  is_used boolean NOT NULL DEFAULT false,
  CONSTRAINT password_reset_tokens_pkey PRIMARY KEY(id)
);


CREATE TABLE refresh_tokens(
  id serial NOT NULL,
  user_id integer NOT NULL,
  token varchar(255) NOT NULL,
  expired_date timestamp NOT NULL,
  is_revoked boolean NOT NULL DEFAULT false,
  CONSTRAINT refresh_tokens_pkey PRIMARY KEY(id)
);

CREATE TABLE sales(
  id serial NOT NULL,
  user_id integer NOT NULL,
  customer_id integer NOT NULL,
  sales_serial varchar(255) NOT NULL,
  total numeric(15,2) NOT NULL,
  sale_date_time timestamp DEFAULT CURRENT_TIMESTAMP,
  igv numeric(15,2) NOT NULL,
  CONSTRAINT sales_pkey PRIMARY KEY(id)
);

CREATE TABLE customers(
  id serial NOT NULL,
  dni varchar(10) NOT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(50),
  phone varchar(20) NOT NULL,
  CONSTRAINT customers_pkey PRIMARY KEY(id)
);

CREATE TABLE sale_details(
  id serial NOT NULL,
  quantity Integer NOT NULL,
  --price numeric(15,2) NOT NULL,
  sale_id integer NOT NULL,
  product_id integer NOT NULL,
  CONSTRAINT sale_details_pkey PRIMARY KEY(id)
);

CREATE TABLE products(
  id serial NOT NULL,
  name varchar(50) NOT NULL,
  description varchar(100) NOT NULL,
  price numeric(15,2) NOT NULL,
  stock integer NOT NULL,
  status boolean NOT NULL DEFAULT false,
  CONSTRAINT products_pkey PRIMARY KEY(id)
);

ALTER TABLE sales
    ADD CONSTRAINT sale_user_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES users (id)
;

ALTER TABLE sales
    ADD CONSTRAINT sale_customer_customer_id_fkey
    FOREIGN KEY (customer_id) REFERENCES customers(id)
;
ALTER TABLE sale_details
    ADD CONSTRAINT sale_details_sale_id_fkey
    FOREIGN KEY (sale_id) REFERENCES sales(id)
;

ALTER TABLE sale_details
    ADD CONSTRAINT sale_details_product_id_fkey
    FOREIGN KEY (product_id) REFERENCES products(id)
;

ALTER TABLE users_roles
  ADD CONSTRAINT users_roles_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES users (id)
;


ALTER TABLE users_roles
  ADD CONSTRAINT users_roles_role_id_fkey
    FOREIGN KEY (role_id) REFERENCES roles (id)
;


ALTER TABLE email_verification_tokens
  ADD CONSTRAINT email_verification_tokens_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES users (id)
;


ALTER TABLE password_reset_tokens
  ADD CONSTRAINT password_reset_tokens_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES users (id)
;


ALTER TABLE refresh_tokens
  ADD CONSTRAINT refresh_tokens_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES users (id)
;

