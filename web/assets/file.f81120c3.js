import{r}from"./index.c7fb5b67.js";async function c(e){return new Promise(async a=>{const{success:s,content:o,message:t}=await r.post({url:"/file/upload/image",data:e,headers:{"Content-Type":"application/form-data"}});window.$message[s?"success":"error"](t),a(o)})}export{c as u};
