var h=Object.defineProperty;var d=Object.getOwnPropertySymbols;var k=Object.prototype.hasOwnProperty,b=Object.prototype.propertyIsEnumerable;var p=(t,e,a)=>e in t?h(t,e,{enumerable:!0,configurable:!0,writable:!0,value:a}):t[e]=a,r=(t,e)=>{for(var a in e||(e={}))k.call(e,a)&&p(t,a,e[a]);if(d)for(var a of d(e))b.call(e,a)&&p(t,a,e[a]);return t};import{_}from"./index.5a8b8fed.js";import{c as u,o as s,f,g as v,t as w,a0 as i,v as B,x as S,r as x,j as c,k as n,a1 as C,a2 as I,a3 as H,w as E,l as $,a4 as z,a5 as A}from"./vendor.e2990882.js";const K="#ffffff",O="#f1c40f",Q="#f6f6f8",D="#9195a1",U="#2AAE67",W="#4098FC",X="#FF69B4",F="0px 0px 0.5rem rgba(0,0,0,0.1)";const G=t=>(B("data-v-029f9373"),t=t(),S(),t),M={key:0,class:"title"},N=G(()=>v("div",{class:"title-pre"},null,-1)),P=u({props:{title:{default:"\u9ED8\u8BA4\u6807\u9898"}},setup(t){const e=t;return(a,l)=>e.title.length?(s(),f("div",M,[N,v("div",null,w(e.title),1)])):i("",!0)}});var R=_(P,[["__scopeId","data-v-029f9373"]]);const T=u({props:{title:null,background:null,withoutGrow:{type:Boolean},canHover:{type:Boolean}},setup(t){const e=t,a={borderRadius:"0.5rem",backgroundColor:"#fff",position:"relative",overflow:"hidden"};e.withoutGrow||(a.flex=1);const l={backgroundImage:`url(${e.background})`,backgroundSize:"cover",color:"#fff",backgroundColor:D},o=x(r({},a));e.background&&(o.value=r(r({},o.value),l));function m(){e.canHover||(o.value.boxShadow=F,o.value.transform="scale(1.02)")}function y(){o.value.boxShadow="",o.value.transform="scale(1)"}return(g,j)=>(s(),f("div",{class:"card",style:I(o.value),onMouseenter:m,onMouseleave:y},[e.title?(s(),c(n(R),{key:0,title:e.title},null,8,["title"])):i("",!0),C(g.$slots,"default",{class:"card-slot"},void 0,!0)],36))}});var V=_(T,[["__scopeId","data-v-73991550"]]);const Y=u({props:{title:null,background:null,withoutGrow:{type:Boolean},canHover:{type:Boolean}},setup(t){const e=t,a=H().default;return(l,o)=>(s(),c(n(V),z(A(e)),{default:E(()=>[n(a)?(s(),c($(n(a)),{key:0})):i("",!0)]),_:1},16))}});export{Y as _,D as a,Q as b,W as c,U as g,X as p,K as w,O as y};