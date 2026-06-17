CREATE TABLE appointments (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT,

    user_name VARCHAR(100),

    doctor_name VARCHAR(100),

    specialization VARCHAR(100),

    appointment_time VARCHAR(50),

    status VARCHAR(30),

    booking_date DATE
);   CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    mobile VARCHAR(15),

    email VARCHAR(100) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    role VARCHAR(20) DEFAULT 'USER'
); INSERT INTO users
(name, mobile, email, password, role)
VALUES
('Akshaya', '9876543210', 'akshaya@gmail.com', 'test123', 'USER');
INSERT INTO appointments
(user_id, user_name, doctor_name, specialization,
 appointment_time, status, booking_date)
VALUES
(1,
 'Akshaya',
 'Dr. Ramesh',
 'Cardiologist',
 '10:00 AM',
 'BOOKED',
 CURRENT_DATE); SELECT * FROM users;
 SELECT * FROM appointments;
 CREATE TABLE doctors(
    id SERIAL PRIMARY KEY,
    doctor_name VARCHAR(100),
    specialization VARCHAR(100),
    available_time VARCHAR(50)
);
INSERT INTO doctors
(doctor_name,specialization,available_time)
VALUES
('Dr. Ramesh','Cardiologist','10:00 AM'),

('Dr. Priya','Dermatologist','11:00 AM'),

('Dr. Kumar','Orthopedic','03:00 PM'),

('Dr. Anjali','Pediatrician','09:00 AM'),

('Dr. Suresh','Neurologist','12:00 PM');
SELECT * FROM doctors;
SELECT * FROM users;
SELECT * FROM appointments;
DELETE FROM users WHERE email='a
