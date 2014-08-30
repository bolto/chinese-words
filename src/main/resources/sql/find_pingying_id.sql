select py.id
from pingying py
left join pingying_character fpy
  on py.first_py_id = fpy.id
left join pingying_character spy
  on py.second_py_id = spy.id
left join pingying_character tpy
  on py.third_py_id = tpy.id
left join tone t
  on t.id = py.tone_id
where
  fpy.symbol = 'ㄒ'
and spy.symbol = 'ㄧ'
and tpy.symbol = '0'
and t.symbol = '';


INSERT INTO `chinesewords`.`pingying`
(`created`,
`first_py_id`,
`second_py_id`,
`third_py_id`,
`tone_id`,
`updated`)
VALUES
(
NOW(),
3,
38,
34,
5,
NOW());

select * from pingying where id = 90;

select * from tone;
