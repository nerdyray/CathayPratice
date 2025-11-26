CREATE Table block_office(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    block_id VARCHAR(10),
    block_name NVARCHAR(10)
);
CREATE Table office_info(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    office_num VARCHAR(10),
    office_add NVARCHAR(30),
    office_block NVARCHAR(10)
);
CREATE Table shelter_info(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    building_type VARCHAR(10),
    shelter_bur VARCHAR(10),
    shelter_add VARCHAR(30),
    shelter_capacity INTEGER(2.0),
    shelter_floor INTEGER(2.0),
    shelter_block VARCHAR(10)


);
CREATE Table block_bur(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    block_name NVARCHAR(10),
    bur_name NVARCHAR(10)
);
CREATE Table bureau_info(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    bureau_code VARCHAR(10),
    bur_name NVARCHAR(10),
    bur_add NVARCHAR(30),
    bur_num CHAR(10)
);
INSERT INTO block_office(block_id, block_name)
VALUES
('C001','大埔里'),
('C002','南祥里'),
('C003','山佳里'),
('C003','埔頂里'),
('C005','綠苗里'),
('C005','綠苗里'),
('C005','綠苗里'),
('C005','綠苗里'),
('C005','綠苗里'),
('C006','民族里'),
('C007','忠孝里'),
('C008','信義里'),
('C008','信義里');

INSERT INTO block_bur(block_name,bur_name)
VALUES
('大埔里','竹南分局'),
('竹南里','竹南分局'),
('山佳里','竹南分局'),
('埔頂里','竹南分局'),
('綠苗里','苗栗分局'),
('民族里','頭份分局'),
('忠孝里','頭份分局'),
('信義里','頭份分局');

INSERT INTO bureau_info(bureau_code,bur_name,bur_add,bur_num)
VALUES
('M001','竹南分局','苗栗縣竹南鎮信義路72號',37474796),
('M002','苗栗分局','苗栗縣頭份市中華路109號',37320059),
('M003','頭份分局','苗栗縣頭份市中興路503號',37663004);

INSERT INTO office_info(office_num,office_add,office_block)
VALUES
(37581072,'竹南鎮公益路1035號','大埔里'),
(37472735,'竹南鎮竹南里中山路103號','竹南里'),
(37641846,'竹南鎮山佳里國光街14號','山佳里'),
(37724839,'後龍鎮埔頂里中興路136-1號','埔頂里'),
(37333240,'苗栗市綠苗里中正路766號','綠苗里'),
(37660001,'民族里民族路96號','民族里'),
(37661145,'忠孝里光大街82號','忠孝里'),
(37616072,'信義里信義路53巷1號','信義里');
  
  -- 4-1
SELECT b.bur_name,b.bur_num -- b.bur..(bureau_info名稱)
FROM shelter_info AS s -- shelter_info別名shelter
JOIN bureau_info AS b -- bureau_info別名shelter
on s.shelter_bur=b.bur_name -- 兩個資料表中的交集
WHERE s.shelter_capacity > 1000; -- 條件是大於1000

  -- 4-2
SELECT 
COUNT(*) AS count_over_1000
FROM shelter_info AS s -- shelter_info別名s
JOIN bureau_info AS b -- bureau_info別名b
    on s.shelter_bur=b.bur_name -- 兩個資料表中的交集
WHERE s.shelter_capacity>1000; -- 條件是大於1000

--4-3
SELECT b.bur_name,b.bur_num,s.building_type,s.shelter_add  -- b.bur..(bureau_info名稱)
FROM shelter_info AS s -- shelter_info別名s
JOIN bureau_info AS b -- bureau_info別名b
on s.shelter_bur=b.bur_name -- 兩個資料表中的交集
WHERE s.shelter_capacity>1000; -- 條件是大於1000

-- 4-4
SELECT b.bur_name,b.bur_num,s.shelter_add,shelter_capacity  -- b.bur..(bureau_info名稱)
FROM shelter_info AS s -- shelter_info別名s
JOIN bureau_info AS b -- bureau_info別名b
on s.shelter_bur=b.bur_name -- 兩個資料表中的交集
JOIN office_info AS o -- office_info別名o
on o.office_block=s.shelter_block -- 兩個資料表中的交集
WHERE s.shelter_add like'%中%'; -- 條件是地址中有'中'

-- 4-5
SELECT s.shelter_add,s.shelter_capacity,o.office_block,o.office_add  -- b.bur..(bureau_info名稱)
FROM shelter_info AS s -- shelter_info別名s
JOIN bureau_info AS b -- bureau_info別名b
on s.shelter_bur=b.bur_name -- 兩個資料表中的交集
JOIN office_info AS o -- office_info別名o
on o.office_block=s.shelter_block
WHERE s.building_type in ('公寓','大樓'); 

-- 5-1
UPDATE shelter_info
SET shelter_capacity=5000
WHERE shelter_add='苗栗縣竹南鎮和平街79號';


DELETE FROM shelter_info
WHERE shelter_capacity<1000;





