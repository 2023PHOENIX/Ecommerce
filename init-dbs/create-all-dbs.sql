-- Connect to the default 'postgres' database
\c postgres;

-- Create the two new databases (Use this simple form if the script runs only once)
CREATE DATABASE authdb;
CREATE DATABASE pctdb;
CREATE DATABASE inventorydb;
CREATE DATABASE orderdb;


-- Or use the conditional form if you run the container multiple times:
-- SELECT 'CREATE DATABASE pctdb'
-- WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'pctdb')\gexec