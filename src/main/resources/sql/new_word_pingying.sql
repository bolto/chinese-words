INSERT INTO `chinesewords`.`word_pingying`
(`created`,
`updated`,
`word_id`,
`pingying_id`,
`list_order`)
VALUES
(NOW(),
NOW(),
12851,
172,
1);


INSERT INTO `chinesewords`.`word`
(
`created`,
`symbol`,
`updated`)
VALUES
(NOW(),
'條',
NOW());


select id from `chinesewords`.`word` where symbol = '條';