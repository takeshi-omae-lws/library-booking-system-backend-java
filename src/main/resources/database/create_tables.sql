drop table if exists returned_article cascade;
create table returned_article (
  returned_article_id SERIAL
  , borrowed_article_id VARCHAR not null
  , occurred_at TIMESTAMP not null
  , constraint returned_article_PKC primary key (returned_article_id)
);

drop table if exists borrowed_articles cascade;
create table borrowed_articles (
  borrowed_article_id VARCHAR
  , customer_id VARCHAR not null
  , article_id VARCHAR not null
  , occurred_at TIMESTAMP not null
  , due_at TIMESTAMP not null
  , constraint borrowed_articles_PKC primary key (borrowed_article_id)
);

drop table if exists borrowing_articles cascade;
create table borrowing_articles (
  borrowing_articles_id SERIAL
  , customer_id VARCHAR not null
  , article_id VARCHAR not null
  , constraint borrowing_articles_PKC primary key (borrowing_articles_id)
);

drop table if exists keep_rules cascade;
create table keep_rules (
  keep_rules_id INTEGER
  , max_amount INTEGER
  , max_new_amount INTEGER not null
  , start_at TIMESTAMP not null
  , constraint keep_rules_PKC primary key (keep_rules_id)
);

drop table if exists return_rules cascade;
create table return_rules (
  return_rules_id INTEGER
  , period INTEGER not null
  , start_at TIMESTAMP not null
  , constraint return_rules_PKC primary key (return_rules_id)
);

drop table if exists borrow_rules cascade;
create table borrow_rules (
  borrow_rules_id INTEGER
  , max_amount INTEGER not null
  , start_at TIMESTAMP not null
  , constraint borrow_rules_PKC primary key (borrow_rules_id)
);

drop table if exists article_categories cascade;
create table article_categories (
  article_category_id SERIAL
  , article_id VARCHAR not null
  , category INTEGER default 0 not null
  , constraint article_categories_PKC primary key (article_category_id)
);

drop table if exists articles cascade;
create table articles (
  article_id VARCHAR
  , status INTEGER default 0 not null
  , constraint articles_PKC primary key (article_id)
);

drop table if exists customers cascade;
create table customers (
  customer_id VARCHAR
  , constraint customers_PKC primary key (customer_id)
);
