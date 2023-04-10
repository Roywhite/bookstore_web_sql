prompt
prompt Creating table BOOK_ADMIN
prompt =========================
prompt
create table HAP_DEV.BOOK_ADMIN
(
  admin_account  NUMBER(38),
  admin_password VARCHAR2(255)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_BOOKS
prompt =========================
prompt
create table HAP_DEV.BOOK_BOOKS
(
  id             NUMBER(6) not null,
  location_name  VARCHAR2(255),
  books_coding   NUMBER(38),
  books_name     VARCHAR2(255),
  books_location VARCHAR2(255),
  books_num      NUMBER(38),
  books_profile  VARCHAR2(255),
  books_rent_num NUMBER(38),
  books_score    NUMBER(38,2)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HAP_DEV.BOOK_BOOKS.id
  is '主键';
comment on column HAP_DEV.BOOK_BOOKS.location_name
  is '店面名称';
comment on column HAP_DEV.BOOK_BOOKS.books_coding
  is '书籍编码';
comment on column HAP_DEV.BOOK_BOOKS.books_name
  is '书籍名';
comment on column HAP_DEV.BOOK_BOOKS.books_location
  is '书籍存放货架地点';
comment on column HAP_DEV.BOOK_BOOKS.books_num
  is '书籍暂存数量';
comment on column HAP_DEV.BOOK_BOOKS.books_profile
  is '书籍简介';
comment on column HAP_DEV.BOOK_BOOKS.books_rent_num
  is '书籍租借次数';
comment on column HAP_DEV.BOOK_BOOKS.books_score
  is '书籍评分';
alter table HAP_DEV.BOOK_BOOKS
  add primary key (ID)
  using index 
  tablespace TBS_PERM_HAP
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_FRIEND_TALK
prompt ===============================
prompt
create table HAP_DEV.BOOK_FRIEND_TALK
(
  id            NUMBER(6) not null,
  user_name     VARCHAR2(255),
  book_name     VARCHAR2(255),
  talk          VARCHAR2(400),
  generate_time DATE,
  account       NUMBER(38)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table HAP_DEV.BOOK_FRIEND_TALK
  add primary key (ID)
  using index 
  tablespace TBS_PERM_HAP
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_HISTORY
prompt ===========================
prompt
create table HAP_DEV.BOOK_HISTORY
(
  order_num          NUMBER(38),
  book_name          VARCHAR2(255),
  rent_location_name VARCHAR2(255),
  residue_day        NUMBER(38)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_LOCATION
prompt ============================
prompt
create table HAP_DEV.BOOK_LOCATION
(
  id                 NUMBER(6) not null,
  location_name      VARCHAR2(255),
  location_full_name VARCHAR2(255)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HAP_DEV.BOOK_LOCATION.id
  is '主键';
comment on column HAP_DEV.BOOK_LOCATION.location_name
  is '店面名称';
comment on column HAP_DEV.BOOK_LOCATION.location_full_name
  is '全址';
alter table HAP_DEV.BOOK_LOCATION
  add primary key (ID)
  using index 
  tablespace TBS_PERM_HAP
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_ORDER
prompt =========================
prompt
create table HAP_DEV.BOOK_ORDER
(
  id                 NUMBER(6) not null,
  account            NUMBER(38),
  order_num          NUMBER(38),
  book_coding        NUMBER(38),
  book_name          VARCHAR2(255),
  rent_location_name VARCHAR2(255),
  time_generate      DATE,
  current_score      NUMBER(38),
  residue_day        NUMBER(38),
  receiver_address   VARCHAR2(255),
  boolean_talk       NUMBER(2)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HAP_DEV.BOOK_ORDER.id
  is '主键';
comment on column HAP_DEV.BOOK_ORDER.account
  is '账号';
comment on column HAP_DEV.BOOK_ORDER.order_num
  is '订单号';
comment on column HAP_DEV.BOOK_ORDER.book_coding
  is '书籍编码';
comment on column HAP_DEV.BOOK_ORDER.book_name
  is '书籍名';
comment on column HAP_DEV.BOOK_ORDER.rent_location_name
  is '租借地点';
comment on column HAP_DEV.BOOK_ORDER.time_generate
  is '发生时间';
comment on column HAP_DEV.BOOK_ORDER.current_score
  is '当时信用分';
comment on column HAP_DEV.BOOK_ORDER.residue_day
  is '租借天数';
comment on column HAP_DEV.BOOK_ORDER.receiver_address
  is '收货地址';
alter table HAP_DEV.BOOK_ORDER
  add primary key (ID)
  using index 
  tablespace TBS_PERM_HAP
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_ORDER_BOOKNAME_LIST
prompt =======================================
prompt
create table HAP_DEV.BOOK_ORDER_BOOKNAME_LIST
(
  book_name    VARCHAR2(255),
  boolean_talk VARCHAR2(255)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_RANKING_LIST
prompt ================================
prompt
create table HAP_DEV.BOOK_RANKING_LIST
(
  book_name  VARCHAR2(255),
  book_score NUMBER(4,2)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HAP_DEV.BOOK_RANKING_LIST.book_name
  is '书名';
comment on column HAP_DEV.BOOK_RANKING_LIST.book_score
  is '评分';

prompt
prompt Creating table BOOK_RENT_SEARCH
prompt ===============================
prompt
create table HAP_DEV.BOOK_RENT_SEARCH
(
  book_rent_name VARCHAR2(255),
  location_name  VARCHAR2(255),
  books_location VARCHAR2(255),
  books_num      NUMBER(38)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_SEARCH
prompt ==========================
prompt
create table HAP_DEV.BOOK_SEARCH
(
  book_name          VARCHAR2(255),
  location_name      VARCHAR2(255),
  location_full_name VARCHAR2(255),
  books_num          NUMBER(38)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HAP_DEV.BOOK_SEARCH.book_name
  is '书名';
comment on column HAP_DEV.BOOK_SEARCH.location_name
  is '书店名';
comment on column HAP_DEV.BOOK_SEARCH.location_full_name
  is '地址';
comment on column HAP_DEV.BOOK_SEARCH.books_num
  is '待租数量';

prompt
prompt Creating table BOOK_SEC
prompt =======================
prompt
create table HAP_DEV.BOOK_SEC
(
  id      NUMBER(6) not null,
  sec_num NUMBER(38)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HAP_DEV.BOOK_SEC.id
  is '主键';
comment on column HAP_DEV.BOOK_SEC.sec_num
  is '秘钥ID';
alter table HAP_DEV.BOOK_SEC
  add primary key (ID)
  using index 
  tablespace TBS_PERM_HAP
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK_USER_PASS
prompt =============================
prompt
create table HAP_DEV.BOOK_USER_PASS
(
  id                NUMBER(6) not null,
  name              VARCHAR2(255),
  account           NUMBER(38),
  password          VARCHAR2(255),
  score             NUMBER(38),
  boolean_rent      NUMBER(38),
  current_order_num NUMBER(38),
  backkey           NUMBER(38),
  residue_degree    NUMBER(38),
  receiver_address  VARCHAR2(255)
)
tablespace TBS_PERM_HAP
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HAP_DEV.BOOK_USER_PASS.id
  is '主键';
comment on column HAP_DEV.BOOK_USER_PASS.name
  is '昵称';
comment on column HAP_DEV.BOOK_USER_PASS.account
  is '账号';
comment on column HAP_DEV.BOOK_USER_PASS.password
  is '密码';
comment on column HAP_DEV.BOOK_USER_PASS.score
  is '信用分';
comment on column HAP_DEV.BOOK_USER_PASS.boolean_rent
  is '租赁状态（1正在，0租赁结束）';
comment on column HAP_DEV.BOOK_USER_PASS.current_order_num
  is '当前订单号';
comment on column HAP_DEV.BOOK_USER_PASS.backkey
  is '密保标记';
comment on column HAP_DEV.BOOK_USER_PASS.residue_degree
  is '剩余续租次数';
comment on column HAP_DEV.BOOK_USER_PASS.receiver_address
  is '收货地址';
alter table HAP_DEV.BOOK_USER_PASS
  add primary key (ID)
  using index 
  tablespace TBS_PERM_HAP
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating sequence BOOK_BOOKS_SEQUENCE
prompt =====================================
prompt
create sequence HAP_DEV.BOOK_BOOKS_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 31
increment by 1
cache 10;

prompt
prompt Creating sequence BOOK_FRIEND_TALK_SEQUENCE
prompt ===========================================
prompt
create sequence HAP_DEV.BOOK_FRIEND_TALK_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 41
increment by 1
cache 10;

prompt
prompt Creating sequence BOOK_LOCATION_SEQUENCE
prompt ========================================
prompt
create sequence HAP_DEV.BOOK_LOCATION_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 11
increment by 1
cache 10;

prompt
prompt Creating sequence BOOK_ORDER_SEQUENCE
prompt =====================================
prompt
create sequence HAP_DEV.BOOK_ORDER_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 81
increment by 1
cache 10;

prompt
prompt Creating sequence BOOK_RANKING_LIST_SEQUENCE
prompt ============================================
prompt
create sequence HAP_DEV.BOOK_RANKING_LIST_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 10;

prompt
prompt Creating sequence BOOK_SEC_SEQUENCE
prompt ===================================
prompt
create sequence HAP_DEV.BOOK_SEC_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 11
increment by 1
cache 10;

prompt
prompt Creating sequence BOOK_USER_PASS_SEQUENCE
prompt =========================================
prompt
create sequence HAP_DEV.BOOK_USER_PASS_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 61
increment by 1
cache 10;

