CREATE TABLE car (
    car_id SERIAL PRIMARY KEY,
    maker VARCHAR(255),
    model VARCHAR(255),
    motor_type VARCHAR(255),
    segment VARCHAR(255)
);

INSERT INTO car (maker, model, motor_type, segment)
VALUES
  ('BMW', 'M3', 'Gasoline', 'Sedan'),
  ('Tesla', 'Model S', 'Electric', 'Sedan');
