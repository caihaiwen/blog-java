## Blog-Java

**基于SpringBoot和Vue3.0开发的个人博客系统** 

前端地址：https://gitee.com/heavenpl/my-blog


### 一、技术栈

- **前端**

  - Http库: [axios](http://www.axios-js.com/)
  - Ui框架: [element-ui](https://element-plus.gitee.io/#/zh-CN/component/installation)
  - 校验组件: [slider-verify-v3](https://github.com/author-fuyf/slider-verify-v3)
  - markdown编辑器: [v-md-editor](https://github.com/code-farmer-i/vue-markdown-editor)
  - 代码高亮:[hightlight.js](https://github.com/highlightjs/highlight.js)
  - Md5加密:[js-md5](https://github.com/amaoxia/js-md5)
  - 项目构建:vue-cli、webpack

- 后端

   -  整合:SpringBoot
   -  项目构建:jdk1.8、Maven3
   -  持久层框架:Mybatis-Plus

- 数据库

   - Redis6.2.5
   - Mysql

  **二、功能需求**

  ​	该博客仅为个人的一个博客，没有进行用户权限的管理，只是简单的区分了一下普通用户和管理员

  **1.普通用户**

   - 查看文章信息：包括文章标题，分类信息，标签信息，发布时间，访问量以及评论信息
   - 查看分类信息：分类信息，分类名称，此分类的名称
   - 搜索文章：可通过文章的标题搜索文章
   - 留言：通过输入评论信息、用户名和邮箱来进行留言
   - 查看友链：查看管理员在友链界面添加的信息

  **2.管理员用户**

  - 拥有普通用户的所有权限
  - 文章管理：新增文章，编辑文章，删除文章
  - 友链管理：添加友链，修改友链，删除友链
  - 分类管理：添加分类，修改分类，删除分类
  - 标签管理：添加分类，修改分类，删除分类
  - 评论管理：登录后在回复评论留言会显示管理员的信息，并显示删除信息按钮，同时对普通用户发送的评论信息进行审核

  

  

