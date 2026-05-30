# AI Code Helper

一个基于AI的编程助手应用，提供智能对话、代码辅助和学习指导功能。

![AI Code Helper](ai-code-helper-frontend/src/assets/hero.png)

## 技术栈

### 后端
- **Spring Boot 3.2.0** - Web框架
- **Java 21** - 开发语言
- **LangChain4j 1.1.0** - AI应用开发框架
- **通义千问(DashScope)** - 大语言模型

### 前端
- **Vue 3** - 前端框架
- **Vite** - 构建工具
- **Axios** - HTTP客户端
- **Markdown-it** - Markdown渲染
- **Highlight.js** - 代码高亮

## 功能特性

- 🤖 智能AI对话
- 💻 代码生成与辅助
- 📚 知识库检索(RAG)
- 🎨 美观的动效界面
- ✍️ Markdown实时渲染
- 📝 代码高亮显示

## 快速开始

### 后端启动

```bash
# 使用Maven编译运行
./mvnw spring-boot:run

# 或者先打包再运行
./mvnw clean package
java -jar target/ai-code-helper-0.0.1-SNAPSHOT.jar
```

后端服务默认运行在 `http://localhost:8080`

### 前端启动

```bash
cd ai-code-helper-frontend

# 安装依赖
npm install

# 开发模式运行
npm run dev

# 生产构建
npm run build
```

前端开发服务器默认运行在 `http://localhost:5173`

## 配置说明

需要配置通义千问API密钥，配置文件位于 `src/main/resources/application.yml` 或创建 `application-local.yml`。

## 项目结构

```
ai-code-helper/
├── ai-code-helper-frontend/  # 前端Vue项目
├── src/main/java/            # 后端Java源码
├── src/main/resources/       # 配置文件与资源
└── pom.xml                   # Maven配置
```

## 许可证

MIT License
