rem *******************************Code Start*****************************
@echo off
set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%%time:~,2%%time:~3,2%"
set "Ymd=%Ymd: =%"
set "filename=matchfee_%Ymd%"
"D:\mysql-5.6.13-win32\bin\mysqldump" --default-character-set=utf8 --opt -R -u root --password=root matchfee >D:\backup\%filename%.sql --hex-blob
C:\7-Zip\7z a D:\backup\%filename%.7z D:\backup\%filename%.sql
del D:\backup\%filename%.sql

copy D:\backup\%filename%.7z z:\matchfee_backup\db\
xcopy D:\matchfee z:\matchfee_backup\attach /s/e/i/y

@echo on
rem *******************************Backup End*****************************

@echo off
rem 自动删除2周之前备份的sql文件
rem /p 指示扫描路径
rem /d -14指示扫描的日期，-14表示14天前(这里的日期是指文件最后修改日期)
rem /m 指示扫描文件类型: *.sql表示所有sql文件
rem /c 后面是要执行的命令内容，用双引号扩起来，cmd /c 后面跟命令内容，@path是扫描到的包含文件名的全路径
forfiles /p D:\backup /d -14 /m *.7z /c "cmd /c del /f @path"

@echo on
rem *******************************Clean End*****************************
