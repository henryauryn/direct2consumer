--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5 (Homebrew)
-- Dumped by pg_dump version 17.5 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: henrylightfoot
--

CREATE TABLE public.customer (
    customer_id integer NOT NULL,
    name character varying(255),
    date_of_birth date,
    contact_details character varying(255)
);


ALTER TABLE public.customer OWNER TO henrylightfoot;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: henrylightfoot
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customer_id_seq OWNER TO henrylightfoot;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: henrylightfoot
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.customer_id;


--
-- Name: customer_product; Type: TABLE; Schema: public; Owner: henrylightfoot
--

CREATE TABLE public.customer_product (
    pc_id integer NOT NULL,
    customer_id integer,
    product_id integer,
    purchase_date date
);


ALTER TABLE public.customer_product OWNER TO henrylightfoot;

--
-- Name: customer_product_pc_id_seq; Type: SEQUENCE; Schema: public; Owner: henrylightfoot
--

CREATE SEQUENCE public.customer_product_pc_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customer_product_pc_id_seq OWNER TO henrylightfoot;

--
-- Name: customer_product_pc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: henrylightfoot
--

ALTER SEQUENCE public.customer_product_pc_id_seq OWNED BY public.customer_product.pc_id;


--
-- Name: log; Type: TABLE; Schema: public; Owner: henrylightfoot
--

CREATE TABLE public.log (
    type character varying,
    further_description character varying,
    log_id integer NOT NULL,
    customer_id integer,
    creation_date date
);


ALTER TABLE public.log OWNER TO henrylightfoot;

--
-- Name: log_id_seq; Type: SEQUENCE; Schema: public; Owner: henrylightfoot
--

CREATE SEQUENCE public.log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.log_id_seq OWNER TO henrylightfoot;

--
-- Name: log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: henrylightfoot
--

ALTER SEQUENCE public.log_id_seq OWNED BY public.log.log_id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: henrylightfoot
--

CREATE TABLE public.product (
    product_id integer NOT NULL,
    scent_name character varying,
    price real,
    volume real,
    quantity character varying,
    product_form character varying
);


ALTER TABLE public.product OWNER TO henrylightfoot;

--
-- Name: product_productid_seq; Type: SEQUENCE; Schema: public; Owner: henrylightfoot
--

CREATE SEQUENCE public.product_productid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_productid_seq OWNER TO henrylightfoot;

--
-- Name: product_productid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: henrylightfoot
--

ALTER SEQUENCE public.product_productid_seq OWNED BY public.product.product_id;


--
-- Name: task; Type: TABLE; Schema: public; Owner: henrylightfoot
--

CREATE TABLE public.task (
    task_id integer NOT NULL,
    type character varying,
    deadline date,
    further_description character varying,
    customer_id integer,
    done boolean NOT NULL
);


ALTER TABLE public.task OWNER TO henrylightfoot;

--
-- Name: task_taskid_seq; Type: SEQUENCE; Schema: public; Owner: henrylightfoot
--

CREATE SEQUENCE public.task_taskid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.task_taskid_seq OWNER TO henrylightfoot;

--
-- Name: task_taskid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: henrylightfoot
--

ALTER SEQUENCE public.task_taskid_seq OWNED BY public.task.task_id;


--
-- Name: customer customer_id; Type: DEFAULT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.customer ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: customer_product pc_id; Type: DEFAULT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.customer_product ALTER COLUMN pc_id SET DEFAULT nextval('public.customer_product_pc_id_seq'::regclass);


--
-- Name: log log_id; Type: DEFAULT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.log ALTER COLUMN log_id SET DEFAULT nextval('public.log_id_seq'::regclass);


--
-- Name: product product_id; Type: DEFAULT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.product ALTER COLUMN product_id SET DEFAULT nextval('public.product_productid_seq'::regclass);


--
-- Name: task task_id; Type: DEFAULT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.task ALTER COLUMN task_id SET DEFAULT nextval('public.task_taskid_seq'::regclass);


--
-- Name: customer customer_pk; Type: CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pk PRIMARY KEY (customer_id);


--
-- Name: customer_product customer_product_pk; Type: CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.customer_product
    ADD CONSTRAINT customer_product_pk PRIMARY KEY (pc_id);


--
-- Name: log log_pk; Type: CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pk PRIMARY KEY (log_id);


--
-- Name: product product_pk; Type: CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pk PRIMARY KEY (product_id);


--
-- Name: task task_pk; Type: CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pk PRIMARY KEY (task_id);


--
-- Name: customer_product customer_product_customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.customer_product
    ADD CONSTRAINT customer_product_customer_fk FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- Name: customer_product customer_product_product_fk; Type: FK CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.customer_product
    ADD CONSTRAINT customer_product_product_fk FOREIGN KEY (product_id) REFERENCES public.product(product_id);


--
-- Name: log log_customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_customer_fk FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- Name: task task_customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: henrylightfoot
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_customer_fk FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- PostgreSQL database dump complete
--

