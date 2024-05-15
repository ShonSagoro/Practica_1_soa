import * as fs from 'fs';
import * as path from 'path';

const tableName = 'inventories';
const modelName='inventory';

const upScript = `
CREATE TABLE IF NOT EXISTS ${tableName} (
    uuid VARCHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    stock VARCHAR(255),
    price VARCHAR(255)
);
`;

const downScript = `
DROP TABLE IF EXISTS ${tableName};
`;

const dir = `./src/infrastructure/migrations/mysq_migrations/`;

// Create directory if it does not exist
fs.mkdirSync(path.resolve(dir), { recursive: true });

fs.writeFileSync(path.resolve(dir, `${modelName}_up.sql`), upScript);
fs.writeFileSync(path.resolve(dir, `${modelName}_down.sql`), downScript);
console.log(`Migrations generated for table ${tableName}`);