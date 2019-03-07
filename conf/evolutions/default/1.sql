# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_category primary key (id)
);

create table ingredient (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_ingredient primary key (id)
);

create table nutritional_data (
  id                            bigint auto_increment not null,
  calories                      integer,
  protein                       integer,
  carbohydrates                 integer,
  fat                           integer,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_nutritional_data primary key (id)
);

create table recipe (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  description                   varchar(255),
  serves                        integer,
  preparation_time              integer,
  nutritional_data_id           bigint,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint uq_recipe_nutritional_data_id unique (nutritional_data_id),
  constraint pk_recipe primary key (id)
);

create table recipe_ingredient (
  recipe_id                     bigint not null,
  ingredient_id                 bigint not null,
  constraint pk_recipe_ingredient primary key (recipe_id,ingredient_id)
);

create table recipe_category (
  recipe_id                     bigint not null,
  category_id                   bigint not null,
  constraint pk_recipe_category primary key (recipe_id,category_id)
);

create table step (
  id                            bigint auto_increment not null,
  description                   varchar(255),
  recipe_id                     bigint,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_step primary key (id)
);

alter table recipe add constraint fk_recipe_nutritional_data_id foreign key (nutritional_data_id) references nutritional_data (id) on delete restrict on update restrict;

create index ix_recipe_ingredient_recipe on recipe_ingredient (recipe_id);
alter table recipe_ingredient add constraint fk_recipe_ingredient_recipe foreign key (recipe_id) references recipe (id) on delete restrict on update restrict;

create index ix_recipe_ingredient_ingredient on recipe_ingredient (ingredient_id);
alter table recipe_ingredient add constraint fk_recipe_ingredient_ingredient foreign key (ingredient_id) references ingredient (id) on delete restrict on update restrict;

create index ix_recipe_category_recipe on recipe_category (recipe_id);
alter table recipe_category add constraint fk_recipe_category_recipe foreign key (recipe_id) references recipe (id) on delete restrict on update restrict;

create index ix_recipe_category_category on recipe_category (category_id);
alter table recipe_category add constraint fk_recipe_category_category foreign key (category_id) references category (id) on delete restrict on update restrict;

create index ix_step_recipe_id on step (recipe_id);
alter table step add constraint fk_step_recipe_id foreign key (recipe_id) references recipe (id) on delete restrict on update restrict;


# --- !Downs

alter table recipe drop constraint if exists fk_recipe_nutritional_data_id;

alter table recipe_ingredient drop constraint if exists fk_recipe_ingredient_recipe;
drop index if exists ix_recipe_ingredient_recipe;

alter table recipe_ingredient drop constraint if exists fk_recipe_ingredient_ingredient;
drop index if exists ix_recipe_ingredient_ingredient;

alter table recipe_category drop constraint if exists fk_recipe_category_recipe;
drop index if exists ix_recipe_category_recipe;

alter table recipe_category drop constraint if exists fk_recipe_category_category;
drop index if exists ix_recipe_category_category;

alter table step drop constraint if exists fk_step_recipe_id;
drop index if exists ix_step_recipe_id;

drop table if exists category;

drop table if exists ingredient;

drop table if exists nutritional_data;

drop table if exists recipe;

drop table if exists recipe_ingredient;

drop table if exists recipe_category;

drop table if exists step;

