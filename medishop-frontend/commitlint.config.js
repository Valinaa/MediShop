export default {
  extends: ['cz'],
  rules: {
    'body-leading-blank': [1, 'always'],
    'body-max-line-length': [2, 'always', 100],
    'footer-leading-blank': [1, 'always'],
    'footer-max-line-length': [2, 'always', 100],
    'header-max-length': [2, 'always', 100],
    'subject-case': [
      2,
      'never',
      ['sentence-case', 'start-case', 'pascal-case', 'upper-case'],
    ],
    'subject-full-stop': [2, 'never', '.'],
    'type-case': [2, 'always', 'lower-case'],
    'type-enum': [
      2,
      'always',
      [
        '✨feat',
        '🐞fix',
        '📝docs',
        '🎨style:',
        '🔨refactor',
        '🐎perf',
        '🔥test',
        '⏪revert',
        '🚧chore',
        '⌛merge',
        '📦build',
        '🚀ci',
        '🔖release',
        '🌈other',
      ],
    ],
  },
}
