/**
 * @typedef {'user' | 'assistant'} MessageRole
 */

/**
 * @typedef {Object} Message
 * @property {string} id
 * @property {MessageRole} role
 * @property {string} content
 * @property {number} timestamp
 * @property {boolean} [loading]
 * @property {boolean} [streaming]
 */

/** @type {Message[]} */
export const mockMessages = [
  {
    id: 'mock-1',
    role: 'user',
    content: '请介绍一下 Java 中的 HashMap，\n以及它和 Hashtable 的区别。',
    timestamp: Date.now() - 120000,
  },
  {
    id: 'mock-2',
    role: 'assistant',
    content: `**HashMap** 是 Java 中最常用的键值对容器，基于数组 + 链表/红黑树实现。

主要特点：
- 允许 \`null\` 键和值
- 线程不安全，性能更好
- JDK 8 以后链表长度超过 8 会转红黑树

\`\`\`java
Map<String, Integer> map = new HashMap<>();
map.put("Java", 1);
map.get("Java"); // 1
\`\`\`

与 **Hashtable** 的区别：
1. Hashtable 线程安全，HashMap 不是
2. Hashtable 不允许 null 键/值
3. HashMap 是 JDK 1.2 引入的新实现，通常优先使用`,
    timestamp: Date.now() - 110000,
  },
  {
    id: 'mock-3',
    role: 'user',
    content: '前端面试需要准备哪些内容？\n帮我列一个清单。',
    timestamp: Date.now() - 60000,
  },
  {
    id: 'mock-4',
    role: 'assistant',
    content: `建议按以下模块准备：

> 基础扎实比刷题数量更重要，每个知识点要能讲清楚原理。

- HTML / CSS：盒模型、Flex、Grid、响应式
- JavaScript：原型链、闭包、Event Loop、Promise
- 框架：Vue 或 React 核心原理、状态管理
- 工程化：Vite / Webpack、模块化、CI/CD
- 网络：HTTP、缓存、跨域、HTTPS

面试前可以整理一份 **项目经历**，重点描述你解决了什么问题。`,
    timestamp: Date.now() - 50000,
  },
]

export function createMessage(role, content, extra = {}) {
  return {
    id: `${Date.now()}-${Math.random().toString(36).slice(2, 8)}`,
    role,
    content,
    timestamp: Date.now(),
    ...extra,
  }
}
