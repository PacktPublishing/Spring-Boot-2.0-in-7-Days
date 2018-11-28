
-- --------------------------------------------------------

--
-- Table structure for table book
--

CREATE TABLE book (
  id bigint(20) NOT NULL,
  description varchar(200) NOT NULL,
  path varchar(40) NOT NULL,
  price double NOT NULL,
  publishdate datetime NOT NULL,
  title varchar(50) NOT NULL,
  p_id bigint(20) DEFAULT NULL,
  bfile longblob NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table orders
--

CREATE TABLE orders (
  ID int(11) NOT NULL,
  AMOUNT double NOT NULL,
  price double NOT NULL,
  order_date date NOT NULL,
  order_num int(11) NOT NULL,
  person_id int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table order_details
--

CREATE TABLE order_details (
  ID int(11) NOT NULL,
  amount double NOT NULL,
  price double NOT NULL,
  order_id int(11) NOT NULL,
  book_id int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table person
--

CREATE TABLE person (
  id bigint(20) NOT NULL,
  firstname varchar(30) NOT NULL,
  lastname varchar(50) NOT NULL,
  password varchar(20) NOT NULL,
  userrole varchar(20) NOT NULL,
  username varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table book
--
ALTER TABLE book
  ADD PRIMARY KEY (id),
  ADD FOREIGN KEY (p_id)
REFERENCES person(ID);

--
-- Indexes for table orders
--
ALTER TABLE orders
  ADD PRIMARY KEY (ID);

ALTER TABLE orders
  ADD FOREIGN KEY(person_id)
REFERENCES person(ID);
--
-- Indexes for table order_details
--
ALTER TABLE order_details
  ADD PRIMARY KEY (ID);
ALTER TABLE order_details
ADD FOREIGN KEY (order_id) 
REFERENCES orders(ID);

ALTER TABLE order_details
ADD FOREIGN KEY (book_id) 
REFERENCES book(ID);

--
-- Indexes for table person
--
ALTER TABLE person
  ADD PRIMARY KEY (id);
COMMIT;