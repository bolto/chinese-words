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
  fpy.symbol = 'ㄊ'
and spy.symbol = 'ㄧ'
and tpy.symbol = 'ㄠ'
and t.symbol = 'ˊ'