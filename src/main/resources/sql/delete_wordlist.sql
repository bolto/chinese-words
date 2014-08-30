use chinesewords;

delete from profile_wordlist where wordlist_id = 56;
delete from test_wordlist where wordlist_id = 56;
delete from wordlist_word where wordlist_id = 56;
delete from wordlist where id = 56;

select * from wordlist;