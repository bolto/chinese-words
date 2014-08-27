UPDATE `chinesewords`.`word_pingying`
SET
`updated` = NOW(),
`list_order` = 1
WHERE `word_id` = 67 AND `pingying_id` = 75;