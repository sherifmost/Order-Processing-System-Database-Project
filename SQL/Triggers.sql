  -- script for adding the triggers
  use bookstore;
  -- constant values for triggers
  SET @book_negative_message = 'Transaction failed.Not enough copies in stock, we only have: ';
  SET @constant_order_value = 50;
  
  -- trigger to prevent updating the number of copies of a certain 
  -- book if it would become negative.
DROP TRIGGER  IF EXISTS book_negative_update;
DELIMITER $$
CREATE TRIGGER book_negative_update
BEFORE UPDATE
ON book FOR EACH ROW
BEGIN
	-- setting an appropriate error message.
    DECLARE errorMessage VARCHAR(255);
    SET errorMessage = CONCAT(@book_negative_message,OLD.copies,' copies.');
    IF new.copies < 0 THEN
        SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = errorMessage;
    END IF;
END $$
DELIMITER ;
 
  -- trigger to add an order if the number of copies of a 
  -- book drop bellow the defined threshold.
DROP TRIGGER  IF EXISTS book_order_update;
DELIMITER $$
CREATE TRIGGER book_order_update
AFTER UPDATE
ON book FOR EACH ROW
BEGIN
    IF (new.copies < new.threshold AND old.copies>= old.threshold)  THEN
		INSERT INTO book_orders VALUES(new.ISBN,@constant_order_value);
    END IF;
END $$
DELIMITER ;

  -- trigger to add to the number of copies of a 
  -- book when an order is deleted (processed).
DROP TRIGGER  IF EXISTS book_order_perform;
DELIMITER $$
CREATE TRIGGER book_order_perform
AFTER DELETE
ON book_orders FOR EACH ROW
BEGIN
    	UPDATE book SET copies = copies + OLD.TotalOrdered WHERE ISBN = OLD.ISBN;
END $$
DELIMITER ;


-- -------------------------------------------------------------------------------------------------------------------- --
  -- This part is to test the triggers.
insert into publisher values('william shakespear','611611891','asuavdyuaddauvad');
insert into book values(12345,'asdjvaud','william shakespear',2012,1500,'Art',10,15);
alter table book modify column copies int;
delete from book_orders where ISBN = 12345;
update book set copies = copies-5  where ISBN = 12345; 


  