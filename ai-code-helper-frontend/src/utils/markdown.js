import MarkdownIt from 'markdown-it'
import DOMPurify from 'dompurify'
import hljs from 'highlight.js'
import multimdTable from 'markdown-it-multimd-table'
import markdownItMark from 'markdown-it-mark'

const md = new MarkdownIt({
  html: false,
  linkify: true,
  breaks: true, // 等同 remarkBreaks：单换行渲染为 <br>
  highlight(str, lang) {
    if (lang && hljs.getLanguage(lang)) {
      try {
        return `<pre class="hljs-wrap"><code class="hljs language-${lang}">${hljs.highlight(str, { language: lang }).value}</code></pre>`
      } catch {
        /* fall through */
      }
    }
    return `<pre class="hljs-wrap"><code class="hljs">${md.utils.escapeHtml(str)}</code></pre>`
  },
})

md.use(multimdTable)
md.use(markdownItMark)

const defaultLinkOpen =
  md.renderer.rules.link_open ||
  ((tokens, idx, options, env, self) => self.renderToken(tokens, idx, options))

md.renderer.rules.link_open = (tokens, idx, options, env, self) => {
  tokens[idx].attrSet('target', '_blank')
  tokens[idx].attrSet('rel', 'noopener noreferrer')
  return defaultLinkOpen(tokens, idx, options, env, self)
}

export function renderMarkdown(text) {
  const html = md.render(text || '')
  return DOMPurify.sanitize(html, {
    ADD_ATTR: ['target', 'rel', 'class'],
  })
}

export function renderPlainText(text) {
  const escaped = md.utils.escapeHtml(text || '')
  return DOMPurify.sanitize(escaped.replace(/\n/g, '<br>'))
}
