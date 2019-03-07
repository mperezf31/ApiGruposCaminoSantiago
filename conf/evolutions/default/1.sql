# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table recipe (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  description                   varchar(255),
  serves                        integer,
  preparation_time              integer,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_recipe primary key (id)
);


# --- !Downs

drop table if exists recipe;

