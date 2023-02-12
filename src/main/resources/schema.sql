CREATE TABLE myUser (
    UUID VARCHAR(255) PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL CHECK (email LIKE '%@%.%'),
    hashpass VARCHAR(255) NOT NULL,
    type ENUM('freelancer', 'investor', 'entrepreneur') NOT NULL CHECK (type IN ('freelancer', 'investor', 'entrepreneur'))
);

CREATE TABLE Entrepreneur (
    UUID INT PRIMARY KEY,
    phone_number VARCHAR(13) CHECK (phone_number ~ '^[0-9]{13}$'),
    domain VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Education (
    UUID INT PRIMARY KEY,
    institution VARCHAR(255),
    degree VARCHAR(255),
    major VARCHAR(255),
    year_of_completion INT,
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Work_Experience (
    UUID INT PRIMARY KEY,
    work_experience VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);

CREATE TABLE Company (
    UUID INT PRIMARY KEY,
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
    UUID INT PRIMARY KEY,
    phone_number VARCHAR(13) CHECK (phone_number ~ '^[0-9]{13}$'),
    skills VARCHAR(255),
    linkedin_link VARCHAR(255) CHECK (linkedin_link ~ '^https://www\.linkedin\.com/in/[a-zA-Z0-9-_]+'),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);


CREATE TABLE Investor (
    UUID INT PRIMARY KEY,
    phone_number VARCHAR(13) CHECK (phone_number ~ '^[0-9]{13}$'),
    domain VARCHAR(255),
    funding_available FLOAT,
    brands_built VARCHAR(255),
    FOREIGN KEY (UUID) REFERENCES myUser(UUID)
);
