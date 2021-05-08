

> 此文章多次提到openid 获取方法
>
>注意：此Api接口内openid需从电脑版内置的浏览器复制
>1. 登录电脑版本微信
>2. 打开河南信息统计学院精华平台
>3. 依次点击 学生-其他
>4. 在弹出窗体内点击同意并等待加载完毕后
>5. 点击复制连接-也就是字母A后边的图标即可复制
    ![logo](https://img-blog.csdnimg.cn/img_convert/13b11cf6db7f43cc9b39ed24e0db7649.png)
# 获取个人资料
* 接口地址：http://www.hntyxxh.com/wechat-api/v2/students
* 返回格式：JSON
* 请求方式：POST
* 请求示例：http://www.hntyxxh.com/wechat-api/v2/students
* 注意：发送请求时请附带请求头openid
### 请求参数说明：


| 请求头 | 必填    | 类型   | 说明                 | 示例                             |
| ------ | ------------ | ------ | -------------------- | -------------------------------- |
| null |  null       | null | null | null |

### 返回参数说明：
#### openid错误或过期返回：

```json
	{
	  "message":"登录信息失效，请退出后重试"
	}
```

#### openid 正确

```javascript
		暂时无法贴代码,返回值存在较多无用数据

		其中包含一些姓名. 性别. 年龄. 等与个人信息相关的数据
		
		且包含一些无用的地区信息
		
		可通过解析json或通过正则方法取出数据
```

# 查询是否需要签到
* 接口地址：http://www.hntyxxh.com/wechat-api/v1/class-attendance/active_sign
* 返回格式：JSON
* 请求方式：POST
* 请求示例：http://www.hntyxxh.com/wechat-api/v1/class-attendance/active_sign
* 注意：发送请求时请附带请求头openid
### 请求参数说明：


| 请求参数 | 必填    | 类型   | 说明                 | 示例                             |
| ------ | ------------ | ------ | ------ | ------ |
| null |  null       | null | null | null |

### 返回参数说明：
#### openid错误或过期返回：

```json
	{
	  "message":"登录信息失效，请退出后重试"
	}
```

#### openid 正确
###### 获取签到课堂返回示例
```json
	{
		"courseId": 3176,
		"signId": 10888,
		"isGPS": 0,
		"isQR": 0
	}
```
###### 无需签到返回示例

```java
	{}  //返回一对花括号说明暂无开启签到的课堂
```

# 提交签到
* 接口地址：http://www.hntyxxh.com/wechat-api/v1/class-attendance/student-sign-in
* 返回格式：JSON
* 请求方式：POST
* 请求示例：http://www.hntyxxh.com/wechat-api/v1/class-attendance/student-sign-in
* 注意：此方法提交参数为（查询是否需要签到）时获得的json。发送请求时请附带请求头openid
### 请求参数说明：


| 请求参数 | 必填    | 类型   | 说明                 | 示例                             |
| ------ | ------------ | ------ | -------------------- | -------------------------------- |
| courseId |  是       | int | 课堂id | 3176 |
| signId |  是       | int | 签到id | 10888 |
| isGPS |  是       | int | 1为GPS签到，0则否 | 0 |
| isQR |  是       | int | 1为二维码签到，0则否 | 0 |

例如：
```json
{"courseId":3176,"signId":10888,"isGPS":0,"isQR":0}
```

### 返回参数说明：
#### openid错误或过期返回：

```json
{
	  "message":"登录信息失效，请退出后重试"
}
```

#### openid 正确 且 签到成功返回
```json
{
    "courseId": 3550,
    "signId": 11535
}
```
# 检查是否有课件需要查看
* 接口地址：http://www.hntyxxh.com/wechat-api/v1/courses/openCoursewares
* 返回格式：JSON
* 请求方式：POST
* 请求示例：http://www.hntyxxh.com/wechat-api/v1/courses/openCoursewares
* 注意：发送请求时请附带请求头openid
### 请求参数说明：

| 请求参数 | 必填    | 类型   | 说明                 | 示例                             |
| ------ | ------------ | ------ | -------------------- | -------------------------------- |
| null |  null       | null | null | null |

### 返回参数说明：
#### openid错误或过期返回：

```json
	{
	  "message":"登录信息失效，请退出后重试"
	}
```

#### openid 正确
* 此接口返回所有科目 以下是经过处理后的返回数据
* 两个重要点：count 和 unreadCount 总共课件数计未读课件数
```json
[{
    "id": 3085,
    "name": "惺惺惜惺惺",
    "cover": "https://app.teachermate.com.cn/6063beadeea6b0c267d8f76147cca905.png",
    "teacherName": "嘻嘻嘻",
    "avatar": "http://www.hntyxxh.com/nas/files/44c69bcb/kerok_msg1599454509",
    "college": "河南信息统计职业学院",
    "code": "D085",
    "department": "大数据教研室",
    "count": 10,
    "unreadCount": 0
}]
```

# 观看课件

> 此处仅提供思路
> 将有未读课件的课堂id保存下来，并拼接成以下链接
> 此处id为当前科目的id，也就是**四、检查是否有课件需要查看**返回值中的id

```awk
http://www.hntyxxh.com/wechat-api/v1/coursewares/{此处替换为课堂id}/student
```
通过此链接的返回值访问课件时，继续带上openid，以进行自动观看课件

# 检查是否有课堂反馈需要提交
* 接口地址：http://www.hntyxxh.com/wechat-api/v3/students/orgFeedbacks
* 返回格式：JSON
* 请求方式：POST
* 请求示例：http://www.hntyxxh.com/wechat-api/v3/students/orgFeedbacks
* 注意：发送请求时请附带请求头openid
### 请求参数说明：

| 请求参数 | 必填    | 类型   | 说明                 | 示例                             |
| ------ | ------------ | ------ | -------------------- | -------------------------------- |
| null |  null       | null | null | null |

### 返回参数说明：
#### openid错误或过期返回：

```json
	{
	  "message":"登录信息失效，请退出后重试"
	}
```

#### openid 正确
* 此接口返回所有科目 以下是经过处理后的返回数据
* 重要点：id 注意获取
```json
[{
    "courseName": "毛泽东思想和中国特色社会主义理论体系概论（3）-物联",
    "code": "D581",
    "teacherName": "宋XX",
    "startTime": "2020-11-05T10:10:00.000Z",
    "id": "6786",
    "type": 2
}
```

# 观看拓展学习

> 看了两眼觉得很简单
>
> 也就没继续爬接口
> 此处只提供思路
1. 获取所有需要观看的课件
2. 判断课件后缀名是ppt还是mp4
3. 若为ppt 则通过抓包可以看到链接内有一个跟页数很像的数据 通过更改直接可以观看完毕
4. 若为mp4 将会每秒自动提交post数据到服务器 数据包内包含时间戳 服务器通过判断第一次提交的时间戳、最后一次提交的时间戳、视频时长。三个参数来判断是否观看完毕整个视频 通过修改第一次的时间戳到很久之前即可直接过视频

反馈或建议或疑问请发送邮件至：flik360@qq.com