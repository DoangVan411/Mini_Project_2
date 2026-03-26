# Team rules for contributings 

## 📋 Quy định Git Workflow

### 1. Branching Strategy (Git Flow)

#### Các loại branch chính:
- **`main`** - Production ready, chỉ nhận merge từ `release` hoặc hotfix
- **`develop`** - Integration branch, chứa code sẵn sàng cho release tiếp theo
- **`feature/*`** - Feature branches, tách từ `develop`
- **`bugfix/*`** - Bug fix branches, tách từ `develop`
- **`hotfix/*`** - Emergency fixes, tách từ `main`

### 2. Commit Message Convention (Conventional Commits)

#### Format:
```
<type>(<scope>): <subject>

<body>

<footer>
```

#### Types:
- **feat**: Feature mới
- **fix**: Bug fix
- **docs**: Documentation
- **style**: Code style (formatting, semicolons, v.v.)
- **refactor**: Refactoring code

#### Ví dụ:
```
feat(auth): thêm đăng nhập bằng email

- Thêm LoginActivity
- Tích hợp Firebase Authentication
- Validate email format

Closes #123
```

```
fix(database): sửa crash khi query user null

Thêm null check tại UserDao method getById
```

### 4. Pull Request (PR) Guidelines

#### Yêu cầu PR:
- Tên PR rõ ràng: `[FEATURE] Mô tả ngắn` hoặc `[BUGFIX] ...`
- Mô tả chi tiết (what, why, how)
- Không có merge conflicts

#### Ví dụ PR description:
```markdown
## Description
Thêm authentication feature cho ứng dụng

## Changes
- Tạo LoginActivity với form email/password
- Integrate Firebase Authentication
- Validate input, show error messages
- Thêm SharedPreferences để lưu token

## Screenshots
[Nếu có UI changes]

Fixes #456
```