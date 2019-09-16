--
-- createdb -U takeshi -E UTF8 -O takeshi lbsbutdb
--
DROP TABLE IF EXISTS article_categories CASCADE;
CREATE TABLE article_categories(
  article_category_id SERIAL NOT NULL
  , article_id VARCHAR
  , category INTEGER
  , PRIMARY KEY (article_category_id)
);

DROP TABLE IF EXISTS articles CASCADE;
CREATE TABLE articles(
  article_id VARCHAR NOT NULL
  , status INTEGER
  , PRIMARY KEY (article_id)
);

DROP TABLE IF EXISTS borrow_rules CASCADE;
CREATE TABLE borrow_rules(
  borrow_rule_id VARCHAR NOT NULL
  , max_amount INTEGER
  , start_at TIMESTAMP
  , PRIMARY KEY (borrow_rule_id)
);

DROP TABLE IF EXISTS borrowed_articles CASCADE;
CREATE TABLE borrowed_articles(
  borrowed_article_id VARCHAR NOT NULL
  , article_id VARCHAR
  , customer_id VARCHAR
  , due_at TIMESTAMP
  , occurred_at TIMESTAMP
  , PRIMARY KEY (borrowed_article_id)
);

DROP TABLE IF EXISTS borrowing_articles CASCADE;
CREATE TABLE borrowing_articles(
  borrowing_articles_id SERIAL NOT NULL
  , article_id VARCHAR
  , customer_id VARCHAR
  , PRIMARY KEY (borrowing_articles_id)
);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers(
  customer_id VARCHAR NOT NULL
  , PRIMARY KEY (customer_id)
);

DROP TABLE IF EXISTS keep_rules CASCADE;
CREATE TABLE keep_rules(
  keep_rule_id VARCHAR NOT NULL
  , max_amount INTEGER
  , max_new_amount INTEGER
  , start_at TIMESTAMP
  , PRIMARY KEY (keep_rule_id)
);

DROP TABLE IF EXISTS return_rules CASCADE;
CREATE TABLE return_rules(
  return_rule_id VARCHAR NOT NULL
  , period INTEGER
  , start_at TIMESTAMP
  , PRIMARY KEY (return_rule_id)
);

DROP TABLE IF EXISTS returned_articles CASCADE;
CREATE TABLE returned_articles(
  returned_article_id SERIAL NOT NULL
  , borrowed_article_id VARCHAR
  , occurred_at TIMESTAMP
  , PRIMARY KEY (returned_article_id)
);
