/* eslint-disable ts/no-unsafe-member-access */
/* eslint-disable ts/no-unsafe-assignment */
import antfu from '@valinaa/eslint-config'
import htmlParser from '@html-eslint/parser'
import eslintHtmlPlugin from '@html-eslint/eslint-plugin'
import eslintUnicornPlugin from 'eslint-plugin-unicorn'
import tsdocPlugin from 'eslint-plugin-tsdoc'

// import eslintJson from './.eslintrc-auto-import.json' assert { type: 'json' }

export default antfu(
  {
    unocss: true,
    jsx: false,
    typescript: {
      tsconfigPath: 'tsconfig.json',
    },
    languageOptions: {
      // globals: {
      //   ...eslintJson.globals,
      // },
      parserOptions: {
        extraFileExtensions: ['.vue', '.ts', '.tsx', '.js', '.jsx'],
        project: ['./tsconfig.json'],
      },
    },
    rules: {
      'antfu/top-level-function': 'off',
      'style/spaced-comment': 'off',
      'import/no-commonjs': 'error',
      'import/no-amd': 'error',
      'import/no-useless-path-segments': 'error',
      'import/no-import-module-exports': 'error',
      'import/exports-last': 'error',
      'eslint-comments/no-unused-disable': 'error',
    },
    ignores: ['node_modules', 'dist'],
  },
  {
    files: ['*.html', '**/*.html'],
    languageOptions: {
      parser: htmlParser,
    },
    plugins: {
      '@html-eslint': eslintHtmlPlugin,
    },
    rules: {
      ...eslintHtmlPlugin.configs.recommended.rules,
      '@html-eslint/indent': ['error', 2],
      '@html-eslint/lowercase': 'error',
      '@html-eslint/no-multiple-empty-lines': 'error',
      '@html-eslint/sort-attrs': 'error',
      '@html-eslint/no-trailing-spaces': 'error',
      '@html-eslint/no-script-style-type': 'error',
    },
  },
  {
    plugins: {
      unicorn: eslintUnicornPlugin,
      tsdoc: tsdocPlugin,
    },
    rules: {
      ...eslintUnicornPlugin.configs['flat/recommended'].rules,
      'unicorn/filename-case': ['error', {
        cases: {
          kebabCase: true,
          pascalCase: true,
        },
      }],
      'tsdoc/syntax': 'warn',
    },
  },
)
