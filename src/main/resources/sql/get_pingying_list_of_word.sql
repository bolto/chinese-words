select 
  wp.word_id, wp.pingying_id, fpy.symbol as first_py, spy.symbol as second_py, tpy.symbol as third_py, t.symbol as tone, wp.list_order
from word w
left join word_pingying wp
  on w.id = wp.word_id
left join pingying py
  on wp.pingying_id = py.id
left join pingying_character fpy
  on fpy.id = py.first_py_id
left join pingying_character spy
  on spy.id = py.second_py_id
left join pingying_character tpy
  on tpy.id = py.third_py_id
left join tone t
  on t.id = py.tone_id
where
  w.symbol = '數'