var se=Object.defineProperty,ue=Object.defineProperties;var re=Object.getOwnPropertyDescriptors;var G=Object.getOwnPropertySymbols;var ie=Object.prototype.hasOwnProperty,ce=Object.prototype.propertyIsEnumerable;var J=(n,a,t)=>a in n?se(n,a,{enumerable:!0,configurable:!0,writable:!0,value:t}):n[a]=t,Z=(n,a)=>{for(var t in a||(a={}))ie.call(a,t)&&J(n,t,a[t]);if(G)for(var t of G(a))ce.call(a,t)&&J(n,t,a[t]);return n},K=(n,a)=>ue(n,re(a));import{as as ne,aI as de,r,a7 as oe,ao as _e,a9 as pe,c as b,o as h,f as k,h as l,w as g,g as p,k as e,U as Q,_ as A,F as Y,i as W,ad as X,t as $,Z as F,j as ee,af as me,a0 as N,aG as ge,aH as ve,ae as fe,y as he,a2 as Ce}from"./vendor.e2990882.js";import{c as ye,d as le,e as ke,f as Ie,h as De,i as $e}from"./article.21f40c66.js";import{d as Be}from"./dayjs.min.617c4415.js";import{a as xe,C as we}from"./CpnLoadMore.b75e1b45.js";import{_ as w}from"./CpnBlockCard.10c6ed92.js";import{d as te}from"./default-avatar.7ca997ff.js";import{u as Fe,_ as V}from"./index.dbe1cbcb.js";import{C as Pe}from"./CpnNavList.d7f1a906.js";import"./BaseTitle.7b69d1b1.js";const j="... \u52A0\u8F7D\u4E2D";function Ue(){const n=ne(),a=de(),{articleId:t}=a.params,i=r(4),c=r(0),m=r(j),v=r(j),C=r(j),f=r(j);r(-1);async function d(){n.start();const s=await ye(parseInt(t));n.finish(),i.value=s.tag,c.value=s.likeCount||0,v.value=s.authorName,C.value=s.createTime,f.value=s.title,m.value=s.content}return d(),{content:m,tag:i,likeCount:c,authorName:v,createTime:C,title:f,articleId:t}}function Le(n){const a=r(null),t=r([]),i=r(null);function c(){var d;const v=(d=a.value)==null?void 0:d.$el.querySelectorAll("h1,h2,h3,h4,h5,h6"),C=Array.from(v).filter(s=>!!s.innerText.trim());if(!C.length)return;const f=Array.from(new Set(C.map(s=>s.tagName))).sort();t.value=C.map(s=>({title:s.innerText,lineIndex:s.getAttribute("data-v-md-line"),indent:f.indexOf(s.tagName)}))}function m(v){var s;const{lineIndex:C}=v,d=((s=a.value)==null?void 0:s.$el).querySelector(`[data-v-md-line="${C}"]`);d&&d.scrollIntoView()}return oe(n,()=>{n.value.length&&n.value!=="..."&&_e(()=>{c()})}),{preview:a,titles:t,detailWrapper:i,handleAnchorClick:m}}function Ae(n){const a=Number(n||0),t=ne(),i=r(0),c=r(1),m=r([]);async function v(d,s=5){t.start();const{commentCount:y,commentList:I}=await le({articleId:a,page:d});t.finish(),I.map(D=>{D.gmtCreate=Be().format("YYYY MM DD"),D.currentPage=1,D._isOpen=!1}),m.value=I,i.value=Math.ceil(y/s)}function C(){v(1)}function f(d){c.value=d,v(d)}return pe(()=>{v(1)}),{totalPage:i,currentPage:c,currentList:m,onCommentUpdate:C,changeCurrentPage:f}}const ae="\u8F93\u5165\u56DE\u590D\u7684\u5185\u5BB9";function Ee(n,a){const t=r(""),i=r(""),c=r(-1),m=r(-1),C=Fe().userId,f=r(!1),d=r(ae),s=r(!1),y=r(-1),I=r(new Map),D=r(!1),P=r();async function U(u=!1){f.value=!0;const x={content:u?i.value:t.value,parentId:c.value,commenterId:C,articleId:n,commentedId:m.value,rootId:y.value};await ke(x)&&(t.value="",i.value="",s.value=!1,u&&L(1)),f.value=!1}async function L(u=1){var _;D.value=!0;const{commentList:x}=await le({articleId:n,page:u,rootId:y.value});D.value=!1,I.value.has(y.value)||I.value.set(y.value,new Map),(_=I.value.get(y.value))==null||_.set(u,x)}function S(u,x,{nick:_,userId:q}){y.value=x,s.value=!0,c.value=u,m.value=q,d.value=`\u56DE\u590D ${_} :`}function R(){c.value=-1,m.value=-1,d.value=ae,i.value=""}async function E(){await U(!0)}async function B(){c.value=-1,y.value=-1,await U(),a("onCommentUpdate")}function H(u){t.value=u}function T(u){i.value=u}function o(u){a("changeCurrentPage",u)}function M(u){P.value.currentPage=u,y.value=P.value.id,L(u)}function O(u){c.value=u.id,y.value=u.id,L(),u._isOpen=!u._isOpen}function z(u){P.value=u}return{content:t,parentId:c,loading:f,replyLoading:D,modalShow:s,replyPlaceHolder:d,replyContent:i,replyList:I,replyHandle:E,commentHandle:B,changeCommentValue:H,changeCurrentPage:o,changeReplyHandle:O,changeReplyValue:T,replyOpenHandle:S,replyCloseHandle:R,getReplyList:L,pageChangeHandle:M,changeCurrentTarget:z}}const He={class:"comment-list"},Ne={class:"comment-area"},Re=F("\u8BC4\u8BBA"),Te={class:"comment-item-header"},be=["onClick"],Ve={class:"username"},Se={class:"floor"},Me={class:"comment-item-main"},Oe={class:"comment-item-footer"},ze=["onClickCapture"],je={class:"reply-list-main"},qe=["onClick"],Ye=["onClick"],We={class:"content"},Ge={key:1,class:"main-item"},Je={key:2,class:"main-item"},Ze=F("\u6211\u6709\u8BDD\u8BF4 \u{1F603}"),Ke={class:"reply-modal"},Qe={class:"modal-main"},Xe=F("\u53D1\u8868\u56DE\u590D"),et=b({props:{currentPage:null,totalPage:null,currentList:null,articleId:null},emits:["onCommentUpdate","changeCurrentPage"],setup(n,{emit:a}){const t=n,{loading:i,content:c,modalShow:m,replyContent:v,replyPlaceHolder:C,replyList:f,replyLoading:d,replyHandle:s,commentHandle:y,changeCommentValue:I,changeCurrentPage:D,changeReplyHandle:P,replyOpenHandle:U,replyCloseHandle:L,changeReplyValue:S,pageChangeHandle:R,changeCurrentTarget:E}=Ee(parseInt(t.articleId),a);function B(H){location.href=`/center/${H}`}return(H,T)=>(h(),k("ul",He,[l(e(w),{title:"\u8BC4\u8BBA \u{1F4AD}","can-hover":!0},{default:g(()=>[p("div",Ne,[l(e(Q),{value:e(c),placeholder:"\u8F93\u5165\u8BC4\u8BBA\u7684\u5185\u5BB9",type:"textarea",autosize:{maxRows:6,minRows:1},maxlength:500,"show-count":"",clearable:"",onUpdateValue:e(I)},null,8,["value","onUpdateValue"]),l(e(A),{secondary:"",type:"success",loading:e(i),disabled:e(i),style:{width:"5rem","align-self":"flex-end"},onClick:e(y)},{default:g(()=>[Re]),_:1},8,["loading","disabled","onClick"])])]),_:1}),(h(!0),k(Y,null,W(t.currentList,o=>(h(),k("div",{key:o.id,class:"comment-item"},[l(e(w),{style:{padding:"1rem"},class:"comment-item","can-hover":!0},{default:g(()=>{var M,O,z,u,x;return[p("div",Te,[l(e(X),{size:50,"object-fit":"cover",src:o.commentUserInfo.imgUrl?`http://112.74.108.218:8080/${o.commentUserInfo.imgUrl}`:e(te)},null,8,["src"]),p("div",{class:"header-info",onClick:_=>B(o.commentUserInfo.userId)},[p("span",Ve,$(o.commentUserInfo.nick||"\u4F5A\u540D"),1),p("span",Se,$(`${o.gmtCreate}`),1)],8,be)]),p("div",Me,$(o.content||"..."),1),p("div",Oe,[l(e(A),{type:"warning",quaternary:"",onClick:_=>e(P)(o)},{default:g(()=>[F($(`\u{1F4AC} ${o.commentCount||0} `),1)]),_:2},1032,["onClick"]),l(e(A),{type:"error",quaternary:""},{default:g(()=>[F($(`\u{1F60D} ${o.likeCount}`),1)]),_:2},1024)]),o._isOpen?(h(),k("div",{key:0,class:"comment-reply-list",onClickCapture:_=>e(E)(o)},[p("ul",je,[(h(!0),k(Y,null,W((M=e(f).get(o.id))==null?void 0:M.get(o.currentPage),_=>(h(),k("li",{key:_.id,class:"main-item",onClick:q=>e(U)(_.id,o.id,_.commentUserInfo)},[l(e(X),{size:30,"object-fit":"cover",src:_.commentUserInfo.imgUrl?`http://112.74.108.218:8080/${_.commentUserInfo.imgUrl}`:e(te)},null,8,["src"]),p("span",{class:"username",onClick:q=>B(_.commentUserInfo.userId)},$(`${_.commentUserInfo.nick||"\u4F5A\u540D"}${_.parentId===-1?"":` \u56DE\u590D ${_.commentedUserInfo.nick}`} : `),9,Ye),p("span",We,$(_.content||"..."),1)],8,qe))),128)),!e(d)&&Math.ceil(o.commentCount/5)>1?(h(),ee(e(me),{key:0,style:{"padding-left":"0.5rem","margin-top":"0.5rem"},page:o.currentPage,"page-count":Math.ceil(o.commentCount/5),"on-update:page":e(R)},null,8,["page","page-count","on-update:page"])):N("",!0),e(d)&&!((z=(O=e(f).get(o.id))==null?void 0:O.get(o.currentPage))!=null&&z.length)?(h(),k("li",Ge," \u8BC4\u8BBA\u52A0\u8F7D\u4E2D ... ")):N("",!0),!e(d)&&!((x=(u=e(f).get(o.id))==null?void 0:u.get(o.currentPage))!=null&&x.length)?(h(),k("li",Je," \u6682\u65E0\u56DE\u590D\uFF0C\u5FEB\u6765\u53D1\u8868\u4F60\u7684\u770B\u6CD5\u5427~ \u{1F601} ")):N("",!0)]),l(e(A),{type:"default",style:{width:"8rem","align-self":"flex-end"},onClick:_=>e(U)(-1,o.id,o.commentUserInfo)},{default:g(()=>[Ze]),_:2},1032,["onClick"])],40,ze)):N("",!0)]}),_:2},1024)]))),128)),l(e(ve),{show:e(m),"onUpdate:show":T[0]||(T[0]=o=>ge(m)?m.value=o:null),onAfterLeave:e(L)},{default:g(()=>[p("div",Ke,[l(e(w),{title:"\u56DE\u590D"},{default:g(()=>[p("div",Qe,[l(e(Q),{placeholder:e(C),type:"textarea",autosize:{maxRows:6,minRows:6},maxlength:500,"show-count":"",value:e(v),onUpdateValue:e(S)},null,8,["placeholder","value","onUpdateValue"]),l(e(A),{type:"primary",style:{width:"8rem","align-self":"flex-end"},onClick:e(s)},{default:g(()=>[Xe]),_:1},8,["onClick"])])]),_:1})])]),_:1},8,["show","onAfterLeave"]),n.currentList.length?(h(),ee(e(xe),{key:0,"current-page":n.currentPage,"total-page":n.totalPage,onChangeCurrentPage:e(D)},null,8,["current-page","total-page","onChangeCurrentPage"])):N("",!0)]))}});var tt=V(et,[["__scopeId","data-v-e1186a40"]]);const at={class:"detail-command"},nt=b({props:{tag:null},setup(n){const a=n,t=r([]);return oe(()=>a.tag,async i=>{const c=await Ie(5,i);t.value=c}),(i,c)=>(h(),k("div",at,[l(e(w),{title:"\u76F8\u5173\u63A8\u8350 \u{1F600}"},{default:g(()=>[l(e(Pe),{list:t.value},null,8,["list"])]),_:1})]))}});var ot=V(nt,[["__scopeId","data-v-94d7f9a4"]]);const lt={class:"detail-interact"},st={class:"detail-interact-button"},ut=F("\u597D\u6587 \u{1F60D}"),rt=F("\u6C34\u6587 \u{1F44E} "),it=b({props:{articleId:null},setup(n){const a=n;function t(){De(a.articleId)}function i(){$e(a.articleId)}return(c,m)=>(h(),k("div",lt,[l(e(w),{title:"\u64CD\u4F5C \u{1F4A6}"},{default:g(()=>[p("div",st,[l(e(A),{type:"error",onClick:t},{default:g(()=>[ut]),_:1}),l(e(A),{type:"primary",onClick:i},{default:g(()=>[rt]),_:1})])]),_:1})]))}});var ct=V(it,[["__scopeId","data-v-05b1f7fc"]]);const dt={class:"detail-info"},_t={class:"detail-info-list"},pt={class:"detail-info-author"},mt={class:"detail-info-time"},gt={class:"detail-info-tags"},vt=b({props:{tag:null,author:null,time:null},setup(n){const a=n,t=[{title:"\u5B66\u4E60",color:"primary"},{title:"\u751F\u6D3B",color:"info"},{title:"\u5410\u69FD",color:"warning"},{title:"\u6811\u6D1E",color:"error"},{title:"\u52A0\u8F7D\u4E2D...",color:"primary"}];return(i,c)=>(h(),k("div",dt,[l(e(w),{title:"\u5173\u4E8E"},{default:g(()=>[p("div",_t,[p("div",pt,$(`\u4F5C\u8005 - ${a.author}`),1),p("div",mt,$(`\u53D1\u5E03\u65F6\u95F4 - ${a.time}`),1),p("div",gt,[l(e(fe),{type:t[n.tag].color},{default:g(()=>[F($(t[n.tag].title),1)]),_:1},8,["type"])])])]),_:1})]))}});var ft=V(vt,[["__scopeId","data-v-a1bab410"]]);const ht={class:"detail"},Ct={class:"detail-main"},yt={key:0,class:"detail-catalogue"},kt={class:"catalogue"},It=["onClick"],Dt={style:{cursor:"pointer"}},$t={name:"PageDetail"},Bt=b(K(Z({},$t),{setup(n){const{content:a,title:t,authorName:i,createTime:c,tag:m,articleId:v}=Ue(),{preview:C,detailWrapper:f,titles:d,handleAnchorClick:s}=Le(a),{totalPage:y,currentPage:I,currentList:D,onCommentUpdate:P,changeCurrentPage:U}=Ae(v);return(L,S)=>{const R=he("v-md-editor");return h(),k("div",ht,[l(e(we),{ref_key:"detailWrapper",ref:f},{leftBox:g(()=>[p("div",Ct,[l(e(w),{title:e(t)||"\u5185\u5BB9\u8BE6\u60C5 \u{1F4D6}",style:{"min-height":"90vh"},"can-hover":!0},{default:g(()=>[l(R,{ref_key:"preview",ref:C,"model-value":e(a),mode:"preview"},null,8,["model-value"])]),_:1},8,["title"]),l(e(tt),{"article-id":e(v),"current-list":e(D),"current-page":e(I),"total-page":e(y),onOnCommentUpdate:e(P),onChangeCurrentPage:e(U)},null,8,["article-id","current-list","current-page","total-page","onOnCommentUpdate","onChangeCurrentPage"])])]),rightBox:g(()=>{var E;return[l(e(ft),{author:e(i),time:e(c),tag:e(m)},null,8,["author","time","tag"]),l(e(ot),{tag:e(m)},null,8,["tag"]),l(e(ct),{ref:"referElement","article-id":Number(e(v))},null,8,["article-id"]),(E=e(d))!=null&&E.length?(h(),k("div",yt,[l(e(w),{title:"\u76EE\u5F55 \u{1F530}"},{default:g(()=>[p("div",kt,[(h(!0),k(Y,null,W(e(d),(B,H)=>(h(),k("div",{key:H,style:Ce({padding:`10px 0 10px ${B.indent*20}px`}),onClick:T=>e(s)(B)},[p("a",Dt,$(B.title),1)],12,It))),128))])]),_:1})])):N("",!0)]}),_:1},512)])}}}));var Rt=V(Bt,[["__scopeId","data-v-9fb31eec"]]);export{Rt as default};
