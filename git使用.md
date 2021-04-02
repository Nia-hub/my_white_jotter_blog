git使用



#### 1.git安装

```markdown
选择一下安装地址，其他的全部默认就行
```

![选择](D:\03-笔记\img\微信截图_20210401194408.png)



##### 1.1.设置全局身份

因为git是分布式版本控制系统，所以每个机器必须自报家门：你的名字和邮箱

```markdown
git config --global user.name 'Nia'
git config --global user.email "1845067261@qq.com"
```

![](D:\03-笔记\img\微信截图_20210401195312.png)



#### 2.使用



##### 2.1.想要将已有项目上传github



- 1.将这个项目变成git可管理的仓库

```mark
进入要上传的项目根目录。执行git init命令，后会出.git文件夹
```

- 2.然后通过git add .(注意这个"."，是有空格的，"."代表这个项目这个文件夹下的目录全部都提交。你也可以通过git add 文件名  提交指定的文件)把文件添加到缓存区



- 3.然后可以通过git status命令，查看下现在的状态，也可以不看

* 4.然后，在使用命令git commit -m "这里面写你的注释"  把文件提交的本地仓库
* 5.下面就到了连接远程仓库（也就是连接Github）

```mariadb
由于本地Git仓库和Github仓库之间的传输是通过SSH加密的，所以连接时需要设置一下：

　　创建SSH KEY。先看一下你C盘用户目录下有没有.ssh目录，有的话看下里面有没有id_rsa和id_rsa.pub这两个文件，有就跳到下一步，没有就通过下面命令创建

　　 $ ssh-keygen -t rsa -C "youremail@example.com"

       然后一路回车。这时你就会在用户下的.ssh目录里找到id_rsa和id_rsa.pub这两个文件  
```



* 6.登录Github,找到右上角的图标，打开点进里面的Settings，再选中里面的SSH and GPG KEYS，点击右上角的New  SSH key，然后Title里面随便填，再把刚才id_rsa.pub里面的内容复制到Title下面的Key内容框里面，最后点击Add SSH  key，这样就完成了SSH Key的加密。具体步骤也可看下面：
* 7.在Github上创建一个Git仓库
* 8.在Github上创建好Git仓库之后我们就可以和本地仓库进行关联了，根据创建好的Git仓库页面的提示，可以在本地test仓库的命令行输入：

```ma
 $ git push -u origin master

由于新建的远程仓库是空的，所以要加上-u这个参数。然后进去GitHub仓库刷新下就会有已经上传的文件夹了
```



#### 3.从仓库中拉取项目，提交代码



##### 3.1.找个放项目的文件然后在打开的git Bash Here中输入 git clone 项目地址

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225134718964.png)

##### 3.2.运行：git add  “想要提交的文件”

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225142121411.png)

##### 

##### 3.3.运行：git commit -m “提交项目的备注”

![]()![20181225142133829](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225142133829.png)



##### 3.4.运行：git push origin master 后仓库就有提交的文件了

 本地仓库代码提交到github上（运行这一步要输入用户名和密码，用户名是github帐号，密码就是github的密码）

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225142204813.png)



##### 3.5.**创建分支**

- 1提交到分支在合并分支到主干，运行：git branch dev 创建分支

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225142824111.png)

- 2运行：git checkout dev 进入创建的分支

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225142907473.png)

- 3运行：git add .

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225144503117.png)

- 4运行：git commit -m “提交代码说明”

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225144512705.png)

- 5运行：git remote add origin url(demo仓库地址)，让本地仓库和远程仓库关联

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225144600300.png)

- 6运行：git push origin dev 本地dev分支仓库提交到github上（运行这一步要输入用户名和密码，用户名是github帐号，密码就是github的密码）；

![](F:\02_gitWorkspace\02-white-jotter-blog\my_white_jotter_blog\img\20181225144722929.png)





https://blog.csdn.net/baiqiangdoudou/article/details/85248782