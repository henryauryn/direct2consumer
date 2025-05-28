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

--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: henrylightfoot
--

COPY public.customer (customer_id, name, date_of_birth, contact_details) FROM stdin;
59	Bree Hodge	1960-01-01	Email: h@h.com Phone: 1-800-HELLO
60	Ethel Cain	2000-06-06	Email: none Phone: none
61	Elizabeth Grant	1989-01-01	Email: liz@liz.com Phone: none
62	George Santos	1985-04-22	Email: hi@gov Phone: none
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: henrylightfoot
--

COPY public.product (product_id, scent_name, price, volume, quantity, product_form) FROM stdin;
1	Baies	58	190	g	Candle
2	Philosykos	160	75	ml	Eau de Parfum
3	Do Son	160	75	ml	Eau de Parfum
4	Figuier	58	190	g	Candle
5	La Droguerie	58	190	g	Candle
6	Do Son	32	150	g	Scented soap
7	Eau Capitale	36	45	ml	Perfumed hand cream
8	Eau Rihla	84	200	g	Perfumed body balm
9	Figuier	290	1500	g	Candle
10	34 Boulevard Saint Germain	179	600	g	Candle
11	L'Eau Papier	135	100	ml	Eau de Toilette
12	Eau des Sense	98	50	ml	Eau de Toilette
13	Eau des Sense	135	100	ml	Eau de Toilette
14	Eau Capitale	56	3	g	Refillable Solid Perfume 
15	L'Ombre dans l'Eau	56	3	g	Refillable Solid Perfume
16	Eau Rose	60	30	ml	Hair Mist
17	Home Fragrance Diffuser	360	2000	ml	Glass vessel
18	Figuier	75	200	ml	Refill for home fragrance diffuser
19	Mimosa	75	200	ml	Refill for home fragrance diffuser
23	Corail Oscuro	255	100	ml	Eau de Parfum
\.


--
-- Data for Name: customer_product; Type: TABLE DATA; Schema: public; Owner: henrylightfoot
--

COPY public.customer_product (pc_id, customer_id, product_id, purchase_date) FROM stdin;
84	62	13	2025-01-18
85	62	23	2024-05-01
86	62	23	2024-05-01
87	62	23	2024-05-01
88	62	23	2024-05-01
56	60	1	2023-05-09
57	60	1	2023-05-09
58	60	2	2025-05-01
59	60	8	2025-02-24
60	60	8	2025-02-24
61	60	8	2025-02-24
62	59	6	2025-02-24
63	59	6	2025-02-24
64	59	6	2025-02-24
65	59	6	2025-02-24
66	59	6	2025-02-24
67	59	6	2025-02-24
68	59	6	2025-02-24
69	59	14	2025-01-29
70	59	10	2025-01-18
71	59	17	2025-01-18
72	59	11	2025-01-18
73	59	1	2025-01-18
74	61	9	2025-01-18
75	61	9	2025-01-18
76	61	9	2025-01-18
77	61	9	2025-01-18
78	61	9	2025-01-18
79	61	9	2025-01-18
80	61	9	2025-01-18
81	61	9	2025-01-18
82	61	9	2025-01-18
83	61	9	2025-01-18
\.


--
-- Data for Name: log; Type: TABLE DATA; Schema: public; Owner: henrylightfoot
--

COPY public.log (type, further_description, log_id, customer_id, creation_date) FROM stdin;
Outgoing email	Order confirmation	31	60	2025-05-27
Outgoing email	Order confirmation	32	59	2025-05-27
Outgoing email	Order confirmation	33	59	2025-04-29
Outgoing email	Order confirmation	34	59	2025-01-28
Outgoing email	Order confirmation	35	62	2025-01-28
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: henrylightfoot
--

COPY public.task (task_id, type, deadline, further_description, customer_id, done) FROM stdin;
36	Send order	2025-05-31	Dispatch	60	f
37	Send order	2025-05-31	Dispatch	60	t
38	Send order	2025-05-16	Dispatch	59	f
40	Escalate to manager	2025-05-16	call	59	t
39	Follow up call	2025-05-16	call	59	t
\.


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: henrylightfoot
--

SELECT pg_catalog.setval('public.customer_id_seq', 62, true);


--
-- Name: customer_product_pc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: henrylightfoot
--

SELECT pg_catalog.setval('public.customer_product_pc_id_seq', 88, true);


--
-- Name: log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: henrylightfoot
--

SELECT pg_catalog.setval('public.log_id_seq', 35, true);


--
-- Name: product_productid_seq; Type: SEQUENCE SET; Schema: public; Owner: henrylightfoot
--

SELECT pg_catalog.setval('public.product_productid_seq', 23, true);


--
-- Name: task_taskid_seq; Type: SEQUENCE SET; Schema: public; Owner: henrylightfoot
--

SELECT pg_catalog.setval('public.task_taskid_seq', 40, true);


--
-- PostgreSQL database dump complete
--

