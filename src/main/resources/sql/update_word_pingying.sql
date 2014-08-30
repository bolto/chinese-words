UPDATE `chinesewords`.`word_pingying`
SET
`updated` = NOW(),
`list_order` = 1
WHERE `word_id` = 3199 AND `pingying_id` = 859;