var HKEY_Root,HKEY_Path,HKEY_Key;
HKEY_Root="HKEY_CURRENT_USER";
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
function pagesetup_settings()
{
try
{
  var Wsh=new ActiveXObject("WScript.Shell");
  HKEY_Key="header";
  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
  HKEY_Key="footer";
  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
  
  HKEY_Key="margin_top"; 
  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0.7875");
  HKEY_Key="margin_bottom"; 
  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0.7875");
  HKEY_Key="margin_left"; 
  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0.7875");
  HKEY_Key="margin_right";
  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0.39375");
  

}
catch(e){alert(e.toString());}
}