CREATE TABLE receipts (
    _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL,
    servings VARCHAR(200) NOT NULL,
    image VARCHAR(200) NOT NULL
);

CREATE TABLE steps (
    _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    receipt_id INTEGER NOT NULL,
    short_description VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    video_url VARCHAR(200) NOT NULL,
    thumbnail_url VARCHAR(200) NOT NULL,
    FOREIGN KEY (receipt_id) REFERENCES receipts (id)
);

CREATE TABLE ingredients (
    _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    receipt_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    measure VARCHAR(10) NOT NULL,
    ingredient VARCHAR(50) NOT NULL,
    FOREIGN KEY (receipt_id) REFERENCES receipts (id)
);