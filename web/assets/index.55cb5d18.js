import{B as F}from"./BaseTitle.4ef018dc.js";import{d as B}from"./default-avatar.7ca997ff.js";import{i as S,u as A,_ as N}from"./index.c7fb5b67.js";import{l as T}from"./lodash.88cb4584.js";import{r as m,a6 as b,a9 as H,a7 as M,ao as j,c as z,o as u,f as i,g as t,F as k,i as I,k as s,h as d,U as D,_ as E,w as L,v as U,x as $,n as q,ad as V,t as C,Z as w}from"./vendor.e2990882.js";function P(){const a=m(!0),c=m(!1),n=m(),o=S(),r=A(),_=b(()=>o.messages),v=b(()=>o.userlist),l=m("");H(()=>{o.init(r.userId);const e=n.value;e.addEventListener("scroll",T.exports.throttle(function(){e.scrollHeight-e.scrollTop>e.clientHeight+200?c.value=!1:c.value=!0},100))}),M(o.messages,async()=>{const e=n.value;await j();const y=e.clientHeight,g=e.scrollHeight,x=()=>{const h=e.scrollTop;if(Math.ceil(h)>=g-y){a.value=!1;return}e.scrollTo(0,h+Math.ceil(g-h)/100),requestAnimationFrame(x)};(a.value||c.value)&&requestAnimationFrame(x)});function p(e){l.value=e}function f(){const e={action:3,chat:{senderId:r.userId,receiverId:-1,content:l.value,sign:0},uuid:-1,createrId:null};c.value=!0,o.send(e),l.value=""}return{messageBox:n,messages:_,user:r,sendValue:l,userlist:v,sendValueChangeValue:p,sendMessage:f}}const R=a=>(U("data-v-5df3ab62"),a=a(),$(),a),W={class:"chat"},Z={class:"chat-box"},G={class:"chat-box-main"},J=R(()=>t("div",{class:"main-title"},"\u54B8\u8A00\u54B8\u8BED",-1)),K={class:"main-item-content"},O={class:"main-input"},Q=w("\u53D1\u9001 \u{1F680}"),X={class:"chat-box-user"},Y={class:"user-title"},ee=z({setup(a){const{messages:c,messageBox:n,user:o,sendValue:r,userlist:_,sendValueChangeValue:v,sendMessage:l}=P();return(p,f)=>(u(),i("div",W,[t("div",Z,[t("div",G,[J,t("ul",{ref_key:"messageBox",ref:n,class:"main-list"},[(u(!0),i(k,null,I(s(c),e=>(u(),i("li",{key:e.id,class:q(["main-item",s(o).userId==e.senderId?" self":""])},[d(s(V),{class:"main-avatar",size:36,round:"","object-fit":"cover",src:e.img_url?`http://112.74.108.218:8080/${e.img_url}`:s(B),"fallback-src":"/src/assets/image/bgc1.jpg"},null,8,["src"]),t("div",K,C(e.content),1)],2))),128))],512),t("div",O,[d(s(D),{placeholder:"\u5728\u6B64\u8F93\u5165\u5185\u5BB9",type:"textarea",autosize:{maxRows:3,minRows:1},maxlength:500,value:s(r),onUpdateValue:s(v)},null,8,["value","onUpdateValue"]),d(s(E),{onClick:s(l)},{default:L(()=>[Q]),_:1},8,["onClick"])])]),t("div",X,[t("div",Y,[d(s(F),{title:`\u5F53\u524D\u5728\u7EBF ${s(_).size}`},null,8,["title"])]),(u(!0),i(k,null,I(s(_).values(),e=>(u(),i("div",{key:e.id,class:"user-item"},[d(s(V),{round:"","object-fit":"cover",src:e.imgUrl?`http://112.74.108.218:8080/${e.imgUrl}`:s(B),"fallback-src":"/src/assets/image/bgc1.jpg"},null,8,["src"]),w(" "+C(e.nick),1)]))),128))])])]))}});var le=N(ee,[["__scopeId","data-v-5df3ab62"]]);export{le as default};