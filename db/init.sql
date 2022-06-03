--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: departments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.departments (
    id integer NOT NULL,
    department_name character varying(100) NOT NULL
);


ALTER TABLE public.departments OWNER TO postgres;

--
-- Name: departments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.departments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.departments_id_seq OWNER TO postgres;

--
-- Name: departments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.departments_id_seq OWNED BY public.departments.id;


--
-- Name: staffs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staffs (
    id integer NOT NULL,
    first_name character varying(40) NOT NULL,
    last_name character varying(40) NOT NULL,
    phone character varying(10) NOT NULL,
    email character varying(40),
    department_id integer NOT NULL,
    registered_time timestamp without time zone
);


ALTER TABLE public.staffs OWNER TO postgres;

--
-- Name: staffs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.staffs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.staffs_id_seq OWNER TO postgres;

--
-- Name: staffs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.staffs_id_seq OWNED BY public.staffs.id;


--
-- Name: departments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments ALTER COLUMN id SET DEFAULT nextval('public.departments_id_seq'::regclass);


--
-- Name: staffs id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staffs ALTER COLUMN id SET DEFAULT nextval('public.staffs_id_seq'::regclass);


--
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.departments (id, department_name) FROM stdin;
1	Üretim
2	AR-GE
3	Pazarlama
4	İnsan Kaynakları
5	Muhasebe
6	Teknik Destek
\.


--
-- Data for Name: staffs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.staffs (id, first_name, last_name, phone, email, department_id, registered_time) FROM stdin;
1	Simge	CİĞERLİOĞLU	6279548733	\N	1	2019-06-28 16:18:15
2	Arzu	BULGUR	1283663610	arzu@abc.com	1	2019-06-20 18:35:52
3	Emre	BİNBAY	7543118133	\N	1	2019-06-15 12:31:03
4	Ayşen	AKSOY	4460059810	aysen@abc.com	1	2019-05-31 12:02:47
5	Ogün	BÖLGE	4577253192	ogun@abc.com	1	2019-06-21 17:09:58
6	Ali	ARDIÇ	3443648051	\N	2	2019-07-17 10:42:37
7	Hulki	BENT	9802723205	\N	2	2019-06-10 12:59:44
8	Atahan	ADANIR	6385818719	\N	2	2019-06-03 18:46:42
9	Emre	AKFIRAT	2757236478	\N	3	2019-07-04 16:14:50
10	Mustafa	ASKIN	1752302801	\N	3	2019-06-22 11:49:33
11	Hayriye	BÜYÜKGÜNGÖR	5672588440	\N	3	2019-07-21 15:37:03
12	Ayla	BAYTIN	3730423155	\N	3	2019-07-18 14:46:51
13	Bahar	ALBAŞ	9223777073	\N	4	2019-07-16 16:28:57
14	Tuğçe	ALTAŞ	3670450820	tugce@abc.com	4	2019-05-27 17:06:46
15	Gamze	ÇINGIR	7617729493	\N	5	2019-07-16 16:05:16
16	Sevtap	ATAN	6548873792	sevtap@abc.com	5	2019-06-13 15:14:54
17	Abdullah	CANER	2207851672	\N	5	2019-06-09 15:29:03
18	Mahire	ÇALIM	9141043131	mahire@abc.com	5	2019-06-16 17:10:24
19	Emine	AKÇA	9844072998	emine@abc.com	6	2019-07-10 12:14:57
20	Zülal	ÇOLAK	7829642287	\N	6	2019-07-09 14:37:05
21	Seher	ÇİFTCİ	2229853431	\N	6	2019-06-24 13:48:29
\.


--
-- Name: departments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.departments_id_seq', 6, true);


--
-- Name: staffs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.staffs_id_seq', 21, true);


--
-- Name: departments departments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);


--
-- Name: staffs staffs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staffs
    ADD CONSTRAINT staffs_pkey PRIMARY KEY (id);


--
-- Name: staffs staffs_department_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staffs
    ADD CONSTRAINT staffs_department_id_fkey FOREIGN KEY (department_id) REFERENCES public.departments(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

