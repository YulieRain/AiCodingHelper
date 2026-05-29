# Python 学习笔记

## 目录
- [环境配置](#环境配置)
- [基础语法](#基础语法)
- [数据类型](#数据类型)
- [控制流](#控制流)
- [函数](#函数)
- [面向对象](#面向对象)
- [常用库](#常用库)

## 环境配置

### 安装Python
1. 访问 python.org 下载安装包
2. 安装时勾选 "Add Python to PATH"
3. 验证安装：`python --version`

### IDE推荐
- **VS Code**：轻量级，插件丰富
- **PyCharm**：功能强大，适合大型项目
- **Jupyter Notebook**：适合数据分析和学习

## 基础语法

### 变量与赋值
```python
# 变量声明
name = "张三"
age = 25
price = 99.99

# 多重赋值
a, b, c = 1, 2, 3
```

### 注释
```python
# 单行注释
'''
多行注释
'''
```

### 数据类型
```python
# 整数、浮点数、字符串、布尔值
x = 10          # int
y = 3.14        # float
text = "Hello"  # str
flag = True     # bool
```

## 数据类型

### 数值类型
```python
# 整数
integer = 42

# 浮点数
float_num = 3.14159

# 复数
complex_num = 1 + 2j

# 运算
print(5 + 3)    # 8
print(5 - 3)    # 2
print(5 * 3)    # 15
print(5 / 2)    # 2.5
print(5 // 2)   # 2 (整除)
print(5 % 2)    # 1 (取余)
print(5 ** 2)   # 25 (幂运算)
```

### 字符串
```python
# 创建字符串
s1 = "Hello"
s2 = 'World'
s3 = """多行
字符串"""

# 字符串操作
print(s1 + s2)      # HelloWorld
print(s1 * 3)       # HelloHelloHello
print(len(s1))      # 5
print(s1[0])        # H
print(s1[1:3])      # el
print("Hi" in s1)   # True

# 格式化
print(f"姓名: {s1}, 欢迎你")  # f-string
print("姓名: {}, 欢迎你".format(s1))
print("姓名: %s, 欢迎你" % s1)
```

### 列表
```python
# 创建列表
fruits = ["苹果", "香蕉", "橙子"]
numbers = [1, 2, 3, 4, 5]

# 列表操作
print(fruits[0])        # 苹果
print(fruits[-1])       # 橙子
print(fruits[0:2])      # ['苹果', '香蕉']
fruits.append("葡萄")   # 添加元素
fruits.insert(1, "芒果")# 插入元素
fruits.remove("香蕉")   # 删除元素
print(len(fruits))      # 列表长度

# 列表推导式
squares = [x**2 for x in range(10)]
print(squares)         # [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
```

### 字典
```python
# 创建字典
person = {
    "name": "张三",
    "age": 25,
    "city": "北京"
}

# 字典操作
print(person["name"])        # 张三
person["email"] = "test@email.com"  # 添加键值对
print(person.get("name"))    # 张三 (安全获取)
print(person.keys())         # 键的列表
print(person.values())       # 值的列表
print(person.items())        # 键值对的列表

# 字典推导式
squares_dict = {x: x**2 for x in range(6)}
print(squares_dict)          # {0: 0, 1: 1, 2: 4, 3: 9, 4: 16, 5: 25}
```

### 元组
```python
# 创建元组
point = (10, 20)
colors = ("红", "绿", "蓝")

# 元组不可变，但可包含可变对象
data = ([1, 2], 3)
data[0].append(3)  # 可以修改内部列表
print(data)        # ([1, 2, 3], 3)
```

## 控制流

### 条件判断
```python
# if-elif-else
score = 85

if score >= 90:
    print("优秀")
elif score >= 80:
    print("良好")
elif score >= 60:
    print("及格")
else:
    print("不及格")

# 嵌套条件
if score >= 90 and score <= 100:
    grade = "A"
elif score >= 80:
    grade = "B"
else:
    grade = "C"

print(f"成绩等级: {grade}")
```

### 循环
```python
# for循环
for i in range(5):
    print(i)  # 0, 1, 2, 3, 4

# for循环遍历列表
fruits = ["苹果", "香蕉", "橙子"]
for fruit in fruits:
    print(f"我喜欢吃: {fruit}")

# while循环
count = 0
while count < 3:
    print(f"计数: {count}")
    count += 1

# break和continue
for i in range(5):
    if i == 2:
        break   # 跳出循环
    if i == 1:
        continue  # 跳过当前迭代
    print(i)  # 0, 3, 4
```

## 函数

### 函数定义
```python
# 简单函数
def greet():
    print("你好！")

# 带参数的函数
def greet_name(name):
    print(f"你好，{name}！")

greet()
greet_name("张三")

# 带返回值的函数
def add(a, b):
    return a + b

result = add(5, 3)
print(f"5 + 3 = {result}")

# 默认参数
def power(base, exponent=2):
    return base ** exponent

print(power(5))      # 25 (使用默认值)
print(power(5, 3))   # 125

# 关键字参数
def describe(name, age, city):
    print(f"我叫{name}，{age}岁，住在{city}")

describe(age=25, city="北京", name="张三")
```

### 变量作用域
```python
# 局部变量
def test():
    x = 10  # 局部变量
    print(x)

test()
# print(x)  # 错误：x未定义

# 全局变量
global_var = "全局变量"

def test():
    global global_var
    global_var = "修改后的全局变量"

print(global_var)  # 全局变量
test()
print(global_var)  # 修改后的全局变量
```

### Lambda表达式
```python
# 匿名函数
square = lambda x: x ** 2
print(square(5))  # 25

# 带多个参数
add = lambda x, y: x + y
print(add(3, 4))  # 7

# 在高阶函数中使用
numbers = [1, 2, 3, 4, 5]
squares = list(map(lambda x: x ** 2, numbers))
print(squares)    # [1, 4, 9, 16, 25]

evens = list(filter(lambda x: x % 2 == 0, numbers))
print(evens)      # [2, 4]
```

## 面向对象

### 类与对象
```python
class Person:
    # 构造方法
    def __init__(self, name, age):
        self.name = name
        self.age = age

    # 实例方法
    def introduce(self):
        print(f"我叫{self.name}，今年{self.age}岁")

    # 类方法
    @classmethod
    def create_adult(cls, name):
        return cls(name, 18)

    # 静态方法
    @staticmethod
    def is_adult(age):
        return age >= 18

# 创建对象
person = Person("张三", 25)
person.introduce()

# 使用类方法
adult = Person.create_adult("李四")
adult.introduce()

# 使用静态方法
print(Person.is_adult(25))   # True
print(Person.is_adult(15))   # False
```

### 继承
```python
class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        print(f"{self.name} 发出声音")

class Dog(Animal):
    def speak(self):
        print(f"{self.name} 汪汪叫")

class Cat(Animal):
    def speak(self):
        print(f"{self.name} 喵喵叫")

# 创建子类对象
dog = Dog("旺财")
dog.speak()  # 旺财 汪汪叫

cat = Cat("咪咪")
cat.speak()  # 咪咪 喵喵叫
```

### 封装
```python
class BankAccount:
    def __init__(self, account_number, balance):
        self.__account_number = account_number  # 私有属性
        self.__balance = balance                # 私有属性

    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            print(f"存款成功，当前余额: {self.__balance}")

    def withdraw(self, amount):
        if amount > self.__balance:
            print("余额不足")
        elif amount > 0:
            self.__balance -= amount
            print(f"取款成功，当前余额: {self.__balance}")

    def get_balance(self):  # 提供访问接口
        return self.__balance

# 使用
account = BankAccount("123456", 1000)
account.deposit(500)      # 存款成功，当前余额: 1500
account.withdraw(300)     # 取款成功，当前余额: 1200
print(account.get_balance())  # 1200
```

## 常用库

### 标准库
```python
# math库
import math
print(math.sqrt(16))      # 4.0
print(math.pi)            # 3.14159...
print(math.factorial(5))  # 120

# random库
import random
print(random.randint(1, 10))  # 1到10的随机整数

# datetime库
from datetime import datetime
now = datetime.now()
print(now.strftime("%Y-%m-%d %H:%M:%S"))

# os库
import os
print(os.getcwd())  # 获取当前目录
print(os.listdir("."))  # 列出当前目录文件

# json库
import json
data = {"name": "张三", "age": 25}
json_str = json.dumps(data)
print(json_str)
```

### 第三方库

#### requests (HTTP请求)
```python
import requests

# 发送GET请求
response = requests.get("https://api.example.com/data")
print(response.status_code)  # 状态码
print(response.json())       # JSON响应
```

#### pandas (数据分析)
```python
import pandas as pd

# 创建DataFrame
data = {
    "姓名": ["张三", "李四", "王五"],
    "年龄": [25, 30, 28],
    "城市": ["北京", "上海", "广州"]
}
df = pd.DataFrame(data)

# 查看数据
print(df.head())
print(df.describe())
```

#### numpy (数值计算)
```python
import numpy as np

# 创建数组
arr = np.array([1, 2, 3, 4, 5])
print(arr)  # [1 2 3 4 5]

# 数组运算
print(arr * 2)      # [2 4 6 8 10]
print(arr + 10)     # [11 12 13 14 15]
print(arr.mean())   # 3.0
print(arr.std())    # 1.414...
```

## 实用技巧

### 列表推导式
```python
# 传统方式
squares = []
for i in range(10):
    squares.append(i**2)

# 推导式方式
squares = [i**2 for i in range(10)]

# 带条件的推导式
even_squares = [i**2 for i in range(10) if i % 2 == 0]
```

### 字典推导式
```python
# 传统方式
pairs = []
for i in range(5):
    pairs.append((i, i**2))

# 推导式方式
pairs = {i: i**2 for i in range(5)}
```

### 上下文管理器
```python
# 文件操作
with open("file.txt", "w") as f:
    f.write("Hello, World!")

# 自动关闭文件，即使发生异常
```

## 学习建议

1. **多写代码**：理论结合实践
2. **读源码**：学习优秀代码
3. **做项目**：通过项目巩固知识
4. **求帮助**：遇到问题及时搜索和求助

---

**持续学习，Python的世界非常广阔！** 🚀
