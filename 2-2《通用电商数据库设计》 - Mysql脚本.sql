drop table if exists t_admin_admin;

drop table if exists t_admin_group;

drop table if exists t_news_class;

drop table if exists t_news_news;

drop table if exists t_order_cart;

drop table if exists t_order_order;

drop table if exists t_order_orderitems;

drop table if exists t_product_brand;

drop table if exists t_product_class;

drop table if exists t_product_product;

drop table if exists t_user_group;

drop table if exists t_user_receive;

drop table if exists t_user_user;

/*==============================================================*/
/* Table: t_admin_admin                                         */
/*==============================================================*/
create table t_admin_admin
(
   adminid              int not null auto_increment,
   admingroupid         int,
   adminname            varchar(50),
   adminpwd             varchar(64),
   gender               char(1),
   regtime              datetime,
   modifytime           datetime,
   description          text,
   primary key (adminid)
);

/*==============================================================*/
/* Table: t_admin_group                                         */
/*==============================================================*/
create table t_admin_group
(
   admingroupid         int not null auto_increment,
   admingroupname       varchar(50),
   primary key (admingroupid)
);

/*==============================================================*/
/* Table: t_news_class                                          */
/*==============================================================*/
create table t_news_class
(
   classId              int not null auto_increment,
   className            varchar(50),
   primary key (classId)
);

/*==============================================================*/
/* Table: t_news_news                                           */
/*==============================================================*/
create table t_news_news
(
   newsId               int not null auto_increment,
   classId              int,
   title                varchar(50),
   content              varchar(30),
   datetime             datetime,
   publisher            varchar(20),
   primary key (newsId)
);

/*==============================================================*/
/* Table: t_order_cart                                          */
/*==============================================================*/
create table t_order_cart
(
   cartid               int not null auto_increment,
   productName          varchar(50),
   productPrice         double,
   productNum           int,
   buytime              datetime,
   primary key (cartid)
);

/*==============================================================*/
/* Table: t_order_order                                         */
/*==============================================================*/
create table t_order_order
(
   orderid              int not null,
   userid               int,
   receiveid            int,
   ordersum             double,
   ordertime            datetime,
   primary key (orderid)
);

/*==============================================================*/
/* Table: t_order_orderitems                                    */
/*==============================================================*/
create table t_order_orderitems
(
   orderitemId          int not null auto_increment,
   orderid              int,
   productName          varchar(50),
   productPrice         double,
   buynum               int,
   primary key (orderitemId)
);

/*==============================================================*/
/* Table: t_product_brand                                       */
/*==============================================================*/
create table t_product_brand
(
   brandid              int not null auto_increment,
   brandname            varchar(50),
   primary key (brandid)
);

/*==============================================================*/
/* Table: t_product_class                                       */
/*==============================================================*/
create table t_product_class
(
   classid              int not null auto_increment,
   classname            varchar(50),
   primary key (classid)
);

/*==============================================================*/
/* Table: t_product_product                                     */
/*==============================================================*/
create table t_product_product
(
   productid            int not null auto_increment,
   classid              int,
   brandid              int,
   productname          varchar(50),
   smallimg             varchar(50),
   bigimg               varchar(50),
   price                double,
   description          text,
   primary key (productid)
);

/*==============================================================*/
/* Table: t_user_group                                          */
/*==============================================================*/
create table t_user_group
(
   usergroupid          int not null auto_increment,
   groupname            varchar(50),
   primary key (usergroupid)
);

/*==============================================================*/
/* Table: t_user_receive                                        */
/*==============================================================*/
create table t_user_receive
(
   receiveid            int not null auto_increment,
   userid               int,
   receivename          varchar(50),
   province             varchar(50),
   tel                  varchar(12),
   address              varchar(100),
   primary key (receiveid)
);

/*==============================================================*/
/* Table: t_user_user                                           */
/*==============================================================*/
create table t_user_user
(
   userid               int not null auto_increment,
   usergroupid          int,
   username             varchar(50),
   userpwd              varchar(32),
   gender               char(1),
   regtime              datetime,
   modifytime           datetime,
   description          text,
   primary key (userid)
);

alter table t_admin_admin add constraint FK_Reference_8 foreign key (admingroupid)
      references t_admin_group (admingroupid) on delete restrict on update restrict;

alter table t_news_news add constraint FK_Reference_16 foreign key (classId)
      references t_news_class (classId) on delete restrict on update restrict;

alter table t_order_order add constraint FK_Reference_5 foreign key (userid)
      references t_user_user (userid) on delete restrict on update restrict;

alter table t_order_order add constraint FK_Reference_6 foreign key (receiveid)
      references t_user_receive (receiveid) on delete restrict on update restrict;

alter table t_order_orderitems add constraint FK_Reference_7 foreign key (orderid)
      references t_order_order (orderid) on delete restrict on update restrict;

alter table t_product_product add constraint FK_Reference_3 foreign key (classid)
      references t_product_class (classid) on delete restrict on update restrict;

alter table t_product_product add constraint FK_Reference_4 foreign key (brandid)
      references t_product_brand (brandid) on delete restrict on update restrict;

alter table t_user_receive add constraint FK_Reference_2 foreign key (userid)
      references t_user_user (userid) on delete restrict on update restrict;

alter table t_user_user add constraint FK_Reference_1 foreign key (usergroupid)
      references t_user_group (usergroupid) on delete restrict on update restrict;
