import{r as a}from"./base.63059160.js";async function g(s){return new Promise(async t=>{const{success:e,message:r}=await a.post({url:"/article/publish",data:s});window.$message[e?"success":"error"](r),t(e)})}async function w({currentPage:s,listSize:t,userId:e,sortKind:r}){return new Promise(async c=>{const{success:n,message:i,content:o}=await a.get({url:`/article/getArticleLists/${s}/${t}${e?"/"+e:""}?sortKind=${r||1}`});n||window.$message.error(i),c(o)})}async function m({currentPage:s,listSize:t,userId:e,key:r,sortKind:c}){return new Promise(async n=>{const{success:i,message:o,content:l}=await a.get({url:`/article/search/${s}/${t}${e?"/"+e:""}?sortKind=${c||1}&key=${r}`});i||window.$message.error(o),n(l)})}async function $(s){return new Promise(async t=>{const{success:e,message:r,content:c}=await a.get({url:`/article/getArticleDetail/${s}`});e||window.$message.error(r),t(c)})}export{w as a,$ as b,m as g,g as p};