use chinesewords;

delete from profile_wordlist where wordlist_id = 58;
delete from test_wordlist where wordlist_id = 58;
delete from wordlist_word where wordlist_id = 58;
delete from wordlist where id = 58;

select * from wordlist;