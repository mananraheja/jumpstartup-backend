CREATE TABLE myUser (
    UUID VARCHAR(255) PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL CHECK (email LIKE '%@%.%'),
    hashpass VARCHAR(255) NOT NULL,
    type ENUM('freelancer', 'investor', 'entrepreneur') NOT NULL CHECK (type IN ('freelancer', 'investor', 'entrepreneur'))
);

CREATE TABLE Entrepreneur (
    UUID VARCHAR(255) PRIMARY KEY,
    phone_number VARCHAR(10) CHECK (phone_number ~ '^[0-9]{10}$'),
    domain VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Education (
    UUID VARCHAR(255) PRIMARY KEY,
    institution VARCHAR(255),
    degree VARCHAR(255),
    major VARCHAR(255),
    year_of_completion INT,
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Work_Experience (
    UUID VARCHAR(255) PRIMARY KEY,
    work_experience VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Company (
    UUID VARCHAR(255) PRIMARY KEY,
    company_name VARCHAR(255),
    is_registered BOOLEAN,
    stakeholder VARCHAR(255),
    company_size INT,
    funding_status ENUM('Seed', 'Series A', 'Series B', 'Series C', 'Public'),
    equity_offered FLOAT,
    assets FLOAT,
    open_to_negotiations BOOLEAN,
    profits_in_last_FY FLOAT,
    pitch VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES Entrepreneur(UUID)
);

CREATE TABLE Freelancer (
    UUID VARCHAR(255) PRIMARY KEY,
    phone_number VARCHAR(10) CHECK (phone_number ~ '^[0-9]{10}$'),
    skills VARCHAR(255),
    linkedin_link VARCHAR(255) CHECK (linkedin_link ~ '^https://www\.linkedin\.com/in/[a-zA-Z0-9-_]+'),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Jobs (
    UUID VARCHAR(255) PRIMARY KEY,
    description VARCHAR(255),
    is_active BOOLEAN,
    number_of_openings INT,
    skills VARCHAR(255),
    pay_estimate FLOAT,
    type ENUM('full-time', 'part-time', 'contract') CHECK (type IN ('full-time', 'part-time', 'contract')),
    posting_date DATE,
    FOREIGN KEY (UUID) REFERENCES Company(UUID)
);

CREATE TABLE Investor (
    UUID VARCHAR(255) PRIMARY KEY,
    phone_number VARCHAR(10) CHECK (phone_number ~ '^[0-9]{10}$'),
    domain VARCHAR(255),
    funding_available FLOAT,
    brands_built VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);
