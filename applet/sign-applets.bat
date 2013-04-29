@for /f %%i in ("%0") do @set curpath=%%~dpi 
@cd /d %curpath%

@for /r . %%X in (*.jar) do jarsigner -keystore ../keys_ld/ld26keystore.ks -storepass ldearscool %%X ld26key