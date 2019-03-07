# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table group_camino (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  description                   varchar(255),
  departure_place               varchar(255),
  departure_date                timestamp,
  arrival_date                  timestamp,
  photo                         varchar(255),
  modality_id                   bigint,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_group_camino primary key (id)
);

create table group_camino_pilgrim (
  group_camino_id               bigint not null,
  pilgrim_id                    bigint not null,
  constraint pk_group_camino_pilgrim primary key (group_camino_id,pilgrim_id)
);

create table modality (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_modality primary key (id)
);

create table pilgrim (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  email                         varchar(255),
  password                      varchar(255),
  photo                         varchar(255),
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_pilgrim primary key (id)
);

create table post (
  id                            bigint auto_increment not null,
  content                       varchar(255),
  group_camino_id               bigint,
  pilgrim_id                    bigint,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_post primary key (id)
);

create index ix_group_camino_modality_id on group_camino (modality_id);
alter table group_camino add constraint fk_group_camino_modality_id foreign key (modality_id) references modality (id) on delete restrict on update restrict;

create index ix_group_camino_pilgrim_group_camino on group_camino_pilgrim (group_camino_id);
alter table group_camino_pilgrim add constraint fk_group_camino_pilgrim_group_camino foreign key (group_camino_id) references group_camino (id) on delete restrict on update restrict;

create index ix_group_camino_pilgrim_pilgrim on group_camino_pilgrim (pilgrim_id);
alter table group_camino_pilgrim add constraint fk_group_camino_pilgrim_pilgrim foreign key (pilgrim_id) references pilgrim (id) on delete restrict on update restrict;

create index ix_post_group_camino_id on post (group_camino_id);
alter table post add constraint fk_post_group_camino_id foreign key (group_camino_id) references group_camino (id) on delete restrict on update restrict;

create index ix_post_pilgrim_id on post (pilgrim_id);
alter table post add constraint fk_post_pilgrim_id foreign key (pilgrim_id) references pilgrim (id) on delete restrict on update restrict;


# --- !Downs

alter table group_camino drop constraint if exists fk_group_camino_modality_id;
drop index if exists ix_group_camino_modality_id;

alter table group_camino_pilgrim drop constraint if exists fk_group_camino_pilgrim_group_camino;
drop index if exists ix_group_camino_pilgrim_group_camino;

alter table group_camino_pilgrim drop constraint if exists fk_group_camino_pilgrim_pilgrim;
drop index if exists ix_group_camino_pilgrim_pilgrim;

alter table post drop constraint if exists fk_post_group_camino_id;
drop index if exists ix_post_group_camino_id;

alter table post drop constraint if exists fk_post_pilgrim_id;
drop index if exists ix_post_pilgrim_id;

drop table if exists group_camino;

drop table if exists group_camino_pilgrim;

drop table if exists modality;

drop table if exists pilgrim;

drop table if exists post;

