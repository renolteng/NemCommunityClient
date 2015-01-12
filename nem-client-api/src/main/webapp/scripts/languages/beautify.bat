
for %%i in (*.js) DO python c:\Python27\Scripts\js-beautify -t %%i > %%i.xxx
for %%i in (*.js) DO move /y %%i.xxx %%i

for %%i in (*.js) DO grep ":" %%i | sed "s/\([^:]*\).*/\1/" > %%i.xxx

@for %%i in (*.xxx) DO @diff -u english.js.xxx %%i
