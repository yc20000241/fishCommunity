import{r as d,u as y,b as D,a as R,o as V,c as x,e as g,f as a,i as e,w as s,R as N,S as h,N as B,T as w,U as F,W as M,Y as k,X as E,t as b}from"./vendor.81235092.js";import{b as A,c as I,u as T}from"./user.d0bbbefc.js";import{a as H,e as L}from"./valid.f10c45cb.js";import{_ as O}from"./CpnBlockCard.d4aaff98.js";import{_ as P}from"./index.1e91d38a.js";import"./base.efe1ff67.js";function S(m){const o=d(!1),f=y(),t=D(),u=d(""),l=d(""),i=d("");function r(){return L(u.value)?!0:(t.error("\u8BF7\u586B\u5199\u6B63\u786E\u7684\u90AE\u7BB1"),!1)}async function p(){if(r()){const{emailToken:n}=await A(u.value);i.value=n,m.value=60}}function c(){return H(l.value)?!0:(t.error("\u9A8C\u8BC1\u7801\u9700\u4E3A\u516D\u4F4D\u6570\u5B57"),!1)}function _(n){u.value=n}function C(n){l.value=n}async function v(){if(r()&&c()){o.value=!0;const n=await I(u.value,i.value,l.value);if(o.value=!1,n)return f.push("./login")}}return{message:t,code:l,email:u,emailTokenR:i,registerLoading:o,sendCodeMessage:p,validEmailCode:c,emailValueChangeHandle:_,emailCodeValueChangeHandle:C,sendRegisterhandle:v}}const G=["onClick"],K={class:"register-main"},U={class:"register-inner"},W=E("\u6CE8\u518C"),X=R({setup(m){const o=y(),{codeMessage:f,cutDown:t}=T(),{registerLoading:u,sendCodeMessage:l,emailValueChangeHandle:i,emailCodeValueChangeHandle:r,sendRegisterhandle:p}=S(t);function c(){o.push("/home")}function _(){o.push("/login")}return(C,v)=>(V(),x("div",{class:"register",onClick:N(c,["self"])},[g("div",K,[a(e(O),{title:"\u6CE8\u518C\u8D26\u53F7 \u{1F533}"},{default:s(()=>[g("div",U,[a(e(F),null,{default:s(()=>[a(e(h),{placeholder:"\u8F93\u5165\u60A8\u7684\u90AE\u7BB1","on-update:value":e(i)},{prefix:s(()=>[a(e(B),{component:e(w)},null,8,["component"])]),_:1},8,["on-update:value"])]),_:1}),a(e(F),null,{default:s(()=>[a(e(h),{placeholder:"\u9A8C\u8BC1\u7801","on-update:value":e(r)},{prefix:s(()=>[a(e(B),{component:e(M)},null,8,["component"])]),_:1},8,["on-update:value"]),a(e(k),{type:"primary",disabled:e(t)>-1,strong:"",onClick:e(l)},{default:s(()=>[E(b(e(f)),1)]),_:1},8,["disabled","onClick"])]),_:1}),g("div",{class:"login",onClick:_},"\u5DF2\u6709\u8D26\u53F7\uFF1F\u8DF3\u8F6C\u767B\u5F55"),a(e(k),{type:"primary",strong:"",loading:e(u),disabled:e(u),onClick:e(p)},{default:s(()=>[W]),_:1},8,["loading","disabled","onClick"])])]),_:1})])],8,G))}});var Q=P(X,[["__scopeId","data-v-c6526a8e"]]);export{Q as default};
